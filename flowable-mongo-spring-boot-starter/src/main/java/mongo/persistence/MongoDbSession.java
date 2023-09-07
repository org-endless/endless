/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mongo.persistence;


import com.mongodb.BasicDBObject;
import com.mongodb.ClientSessionOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.Getter;
import org.bson.conversions.Bson;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.api.FlowableOptimisticLockingException;
import org.flowable.common.engine.impl.db.HasRevision;
import org.flowable.common.engine.impl.interceptor.Session;
import org.flowable.common.engine.impl.persistence.cache.CachedEntity;
import org.flowable.common.engine.impl.persistence.cache.CachedEntityMatcher;
import org.flowable.common.engine.impl.persistence.cache.EntityCache;
import org.flowable.common.engine.impl.persistence.entity.AlwaysUpdatedPersistentObject;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Joram Barrez
 */
@Getter
public class MongoDbSession implements Session {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbSession.class);

    protected MongoDbSessionFactory mongoDbSessionFactory;
    protected MongoClient mongoClient;
    protected MongoDatabase mongoDatabase;
    protected EntityCache entityCache;
    protected Map<Class<? extends Entity>, Map<String, Entity>> insertedObjects = new HashMap<>();
    protected Map<Class<? extends Entity>, Map<String, Entity>> deletedObjects = new HashMap<>();
    protected List<Entity> updatedObjects = new ArrayList<>();
    protected Map<String, List<Bson>> bulkDeletes = new HashMap<>();

    public MongoDbSession(
            MongoDbSessionFactory mongoDbSessionFactory,
            MongoClient mongoClient,
            MongoDatabase mongoDatabase,
            EntityCache entityCache) {
        this(
                mongoDbSessionFactory,
                mongoClient,
                mongoDatabase,
                entityCache,
                mongoClient.startSession(ClientSessionOptions.builder().causallyConsistent(true).build()));
        startTransaction();
    }

    /**
     * Constructor to be used when the client session and transaction is managed externally, passing
     * in the {@link ClientSession}.
     */
    public MongoDbSession(
            MongoDbSessionFactory mongoDbSessionFactory,
            MongoClient mongoClient,
            MongoDatabase mongoDatabase,
            EntityCache entityCache,
            ClientSession clientSession) {
        this.mongoDbSessionFactory = mongoDbSessionFactory;
        this.mongoClient = mongoClient;
        this.mongoDatabase = mongoDatabase;
        this.entityCache = entityCache;
        this.clientSession = clientSession;
    }

    public void startTransaction() {
    }

    @Override
    public void close() {
    }

    public void insertOne(Entity entity) {
        if (entity.getId() == null) {
            String id = CommandContextUtil.getProcessEngineConfiguration().getIdGenerator().getNextId();
            entity.setId(id);
        }

        Class<? extends Entity> clazz = entity.getClass();
        if (!insertedObjects.containsKey(clazz)) {
            insertedObjects.put(
                    clazz, new LinkedHashMap<>()); // order of insert is important, hence LinkedHashMap
        }

        insertedObjects.get(clazz).put(entity.getId(), entity);
        entityCache.put(entity, false); // False -> entity is inserted, so always changed
        entity.setInserted(true);
    }

    @Override
    public void flush() {
        determineUpdatedObjects(); // Needs to be done before the removeUnnecessaryOperations, as
        // removeUnnecessaryOperations will remove stuff from the cache
        removeUnnecessaryOperations();

        if (LOGGER.isDebugEnabled()) {
            debugFlush();
        }

        flushInserts();
        flushUpdates();
        flushDeletes();
    }

    @SuppressWarnings("unchecked")
    protected void flushInserts() {
        if (insertedObjects.isEmpty()) {
            return;
        }

        for (Class<? extends Entity> clazz : insertedObjects.keySet()) {

            LOGGER.debug("inserting type: {}", clazz);

            String collectionName = mongoDbSessionFactory.getClassToCollectionsMap().get(clazz);
            if (collectionName == null) {
                throw new FlowableIllegalArgumentException(
                        "Class " + clazz + " is not mapped to a collection");
            }
            MongoCollection<Document> mongoDbCollection =
                    getMongoDatabase().getCollection(collectionName);

            EntityToDocumentMapper entityMapper = mongoDbSessionFactory.getMapperForEntityClass(clazz);
            Map<String, Entity> entities = insertedObjects.get(clazz);
            if (!entities.isEmpty()) { // Could have 0 elements due to the optimizations before
                List<Document> documents =
                        entities.values().stream()
                                .map(entity -> entityMapper.toDocument(entity))
                                .collect(Collectors.toList());

                if (documents.size() == 1) {
                    mongoDbCollection.insertOne(clientSession, documents.get(0));
                } else {
                    mongoDbCollection.insertMany(clientSession, documents);
                }
            }
        }
    }

    protected void flushUpdates() {

        for (Entity updatedEntity : updatedObjects) {

            LOGGER.debug("updating: {}", updatedEntity);

            Class<?> entityClass = updatedEntity.getClass();
            String collectionName = mongoDbSessionFactory.getClassToCollectionsMap().get(entityClass);
            BasicDBObject updateBasicDBObject =
                    mongoDbSessionFactory
                            .getDataManagerForCollection(collectionName)
                            .createUpdateObject(updatedEntity);
            if (updateBasicDBObject != null) {

                if (updatedEntity instanceof HasRevision) {
                    updateBasicDBObject.append("revision", ((HasRevision) updatedEntity).getRevisionNext());
                }

                MongoCollection<Document> collection = getMongoDatabase().getCollection(collectionName);
                UpdateResult updateResult =
                        collection.updateOne(
                                clientSession,
                                Filters.eq("_id", updatedEntity.getId()),
                                new Document().append("$set", updateBasicDBObject));
                if (updateResult.getModifiedCount() == 0) {
                    throw new FlowableOptimisticLockingException(
                            updatedEntity + " was updated by another transaction concurrently");
                }
            }

            if (updatedEntity instanceof HasRevision) {
                ((HasRevision) updatedEntity).setRevision(((HasRevision) updatedEntity).getRevisionNext());
            }
        }
        updatedObjects.clear();
    }

    public UpdateResult updateImmediately(
            String collection, Bson filter, BasicDBObject updateDBObject) {
        MongoCollection<Document> mongoDbCollection = getCollection(collection);
        return mongoDbCollection.updateOne(
                clientSession, filter, new Document().append("$set", updateDBObject));
    }

    protected void flushDeletes() {

        // Regular deletes
        if (!deletedObjects.isEmpty()) {
            for (Class<? extends Entity> clazz : deletedObjects.keySet()) {

                Map<String, ? extends Entity> entities = deletedObjects.get(clazz);
                if (!entities.isEmpty()) {

                    MongoCollection<Document> mongoDbCollection =
                            getMongoDatabase()
                                    .getCollection(mongoDbSessionFactory.getClassToCollectionsMap().get(clazz));
                    for (Entity entity : entities.values()) {
                        DeleteResult deleteResult =
                                mongoDbCollection.deleteOne(clientSession, Filters.eq("_id", entity.getId()));

                        if (entity instanceof HasRevision && deleteResult.getDeletedCount() == 0) {
                            throw new FlowableOptimisticLockingException(
                                    entity + " was deleted by another transaction concurrently");
                        }
                    }
                }
            }
        }

        // Bulk deletes (no revision check)
        if (!bulkDeletes.isEmpty()) {
            for (String collectionName : bulkDeletes.keySet()) {
                MongoCollection<Document> collection = getCollection(collectionName);

                List<Bson> deleteFilters = bulkDeletes.get(collectionName);
                for (Bson deleteFilter : deleteFilters) {
                    collection.deleteMany(clientSession, deleteFilter);
                }
            }
        }
    }

    public <T> List<T> find(String collection, Bson bsonFilter) {
        FindIterable<Document> documents = findDocuments(collection, bsonFilter);
        return mapToEntities(collection, documents);
    }

    public <T> List<T> find(String collection, Bson bsonFilter, Bson bsonSort) {
        FindIterable<Document> documents = findDocuments(collection, bsonFilter, bsonSort);
        return mapToEntities(collection, documents);
    }

    public <T> List<T> find(String collection, Bson bsonFilter, Bson bsonSort, int limit) {
        FindIterable<Document> documents = findDocuments(collection, bsonFilter, bsonSort, limit);
        return mapToEntities(collection, documents);
    }

    public <T extends Entity> List<T> find(
            String collection,
            Bson bsonFilter,
            Object parameter,
            Class<? extends Entity> entityClass,
            CachedEntityMatcher<T> cachedEntityMatcher) {
        return find(collection, bsonFilter, parameter, entityClass, cachedEntityMatcher, true);
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity> List<T> find(
            String collection,
            Bson bsonFilter,
            Object parameter,
            Class<? extends Entity> entityClass,
            CachedEntityMatcher<T> cachedEntityMatcher,
            boolean checkCache) {
        FindIterable<Document> documents = findDocuments(collection, bsonFilter);
        Collection<? extends Entity> dbEntities = mapToEntitiesType(collection, documents);

        if (checkCache) {

            Collection<CachedEntity> cachedObjects =
                    getEntityCache().findInCacheAsCachedObjects(entityClass);

            if ((cachedObjects != null && cachedObjects.size() > 0)) {

                HashMap<String, Entity> entityMap = new HashMap<>(dbEntities.size());

                // Database entities
                for (Entity entity : dbEntities) {
                    entityMap.put(entity.getId(), entity);
                }

                // Cache entities
                if (cachedObjects != null && cachedEntityMatcher != null) {
                    for (CachedEntity cachedObject : cachedObjects) {
                        T cachedEntity = (T) cachedObject.getEntity();
                        if (cachedEntityMatcher.isRetained(
                                (Collection<T>) dbEntities, cachedObjects, cachedEntity, parameter)) {
                            entityMap.put(
                                    cachedEntity.getId(),
                                    cachedEntity); // will overwrite db version with newer version
                        }
                    }
                }

                dbEntities = entityMap.values();
            }
        }

        // Remove entries which are already deleted
        if (dbEntities.size() > 0) {
            dbEntities.removeIf(this::isEntityToBeDeleted);
        }

        return (List<T>) new ArrayList<>(dbEntities);
    }

    public <T extends Entity> List<T> findFromCache(
            CachedEntityMatcher<T> entityMatcher, Object parameter, Class<? extends Entity> entityClass) {
        Collection<CachedEntity> cachedObjects =
                getEntityCache().findInCacheAsCachedObjects(entityClass);

        List<Entity> result = new ArrayList<>(cachedObjects != null ? cachedObjects.size() : 1);
        if (cachedObjects != null && entityMatcher != null) {
            for (CachedEntity cachedObject : cachedObjects) {
                Entity cachedEntity = cachedObject.getEntity();
                if (entityMatcher.isRetained(null, cachedObjects, (T) cachedEntity, parameter)
                        && !isEntityToBeDeleted(cachedEntity)) {
                    result.add(cachedEntity);
                }
            }
        }

        return (List<T>) result;
    }

    public <T> T findOne(String collection, Bson bsonFilter) {
        return findOne(collection, bsonFilter, null, -1);
    }

    public <T> T findOne(String collection, Bson bsonFilter, Bson sort, int limit) {
        FindIterable<Document> documents = findDocuments(collection, bsonFilter, sort, limit);
        if (documents != null) {
            T entity = mapToEntity(collection, documents);
            if (entity instanceof Entity) {
                String id = ((Entity) entity).getId();
                T cachedEntity =
                        (T)
                                entityCache.findInCache(
                                        mongoDbSessionFactory.getClassForCollection(collection), id);
                if (cachedEntity != null) {
                    return cachedEntity;
                }

                entityCache.put(
                        (Entity) entity,
                        true); // true -> store state so we can see later if it is updated later on
            }

            return entity;
        }
        return null;
    }

    public <T> T mapToEntity(String collection, FindIterable<Document> documents) {
        Iterator<Document> iterator = documents.iterator();
        if (iterator.hasNext()) {
            Document document = iterator.next();
            if (document != null) {
                EntityToDocumentMapper<? extends Entity> entityMapper =
                        mongoDbSessionFactory.getCollectionToMapper().get(collection);
                return (T) entityMapper.fromDocument(document);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> mapToEntities(String collection, FindIterable<Document> documents) {
        EntityToDocumentMapper<? extends Entity> entityMapper =
                mongoDbSessionFactory.getCollectionToMapper().get(collection);
        List<Object> entities = new ArrayList<>();
        for (Document document : documents) {
            entities.add(entityMapper.fromDocument(document));
        }

        return cacheLoadOrStore(entities);
    }

    public List<Entity> mapToEntitiesType(String collection, FindIterable<Document> documents) {
        EntityToDocumentMapper<? extends Entity> entityMapper =
                mongoDbSessionFactory.getCollectionToMapper().get(collection);
        List<Object> entities = new ArrayList<>();
        for (Document document : documents) {
            entities.add(entityMapper.fromDocument(document));
        }

        return cacheLoadOrStore(entities);
    }

    public FindIterable<Document> findDocuments(String collection, Bson bsonFilter) {
        return findDocuments(collection, bsonFilter, null);
    }

    public FindIterable<Document> findDocuments(String collection, Bson bsonFilter, Bson bsonSort) {
        return findDocuments(collection, bsonFilter, bsonSort, 0);
    }

    public FindIterable<Document> findDocuments(
            String collection, Bson bsonFilter, Bson bsonSort, int limit) {
        MongoCollection<Document> mongoDbCollection = getCollection(collection);
        FindIterable<Document> documentResult = null;
        if (bsonFilter != null) {
            documentResult = mongoDbCollection.find(clientSession, bsonFilter);
        } else {
            documentResult = mongoDbCollection.find(clientSession);
        }

        if (bsonSort != null) {
            documentResult = documentResult.sort(bsonSort);
        }

        if (limit > 0) {
            documentResult = documentResult.limit(limit);
        }

        return documentResult;
    }

    @SuppressWarnings("unchecked")
    public <T> T findOne(String collection, String id) {

        T entity =
                (T) entityCache.findInCache(mongoDbSessionFactory.getClassForCollection(collection), id);
        if (entity != null) {
            return entity;
        }

        Document document = findOneDocument(collection, id);
        if (document == null) {
            return null;
        }

        EntityToDocumentMapper<? extends Entity> entityMapper =
                mongoDbSessionFactory.getCollectionToMapper().get(collection);
        entity = (T) entityMapper.fromDocument(document);

        entityCache.put(
                (Entity) entity, true); // true -> store state so we can see later if it is updated later on
        return entity;
    }

    public Document findOneDocument(String collection, String id) {
        Bson filter = Filters.eq("_id", id);
        FindIterable<Document> documents = findDocuments(collection, filter);
        if (documents != null) {
            // TODO: caching
            return documents.first();
        }
        return null;
    }

    public long count(String collection, Bson bsonFilter) {
        MongoCollection<Document> mongoDbCollection = getCollection(collection);
        if (bsonFilter != null) {
            return mongoDbCollection.countDocuments(clientSession, bsonFilter);
        } else {
            return mongoDbCollection.countDocuments(clientSession);
        }
    }

    public void update(Entity entity) {
        entityCache.put(
                entity, false); // false -> we don't store state, meaning it will always be seen as changed
        entity.setUpdated(true);
    }

    public void delete(String collection, Entity entity) {
        Class<? extends Entity> clazz = entity.getClass();
        if (!deletedObjects.containsKey(clazz)) {
            deletedObjects.put(
                    clazz, new LinkedHashMap<>()); // order of insert is important, hence LinkedHashMap
        }
        deletedObjects.get(clazz).put(entity.getId(), entity);
        entity.setDeleted(true);
    }

    public void bulkDelete(String collection, Bson filter) {
        List<Bson> deleteFilters = bulkDeletes.get(collection);
        if (deleteFilters == null) {
            deleteFilters = new ArrayList<>();
            bulkDeletes.put(collection, deleteFilters);
        }
        deleteFilters.add(filter);
    }

    public void determineUpdatedObjects() {
        updatedObjects = new ArrayList<>();
        Map<Class<?>, Map<String, CachedEntity>> cachedObjects = entityCache.getAllCachedEntities();
        for (Class<?> clazz : cachedObjects.keySet()) {

            Map<String, CachedEntity> classCache = cachedObjects.get(clazz);
            for (CachedEntity cachedObject : classCache.values()) {

                Entity cachedEntity = cachedObject.getEntity();

                // Executions are stored as a hierarchical tree, and updates are important to execute
                // even when the execution are deleted, as they can change the parent-child relationships.
                // For the other entities, this is not applicable and an update can be discarded when an
                // update follows.

                if (!isEntityInserted(cachedEntity)
                        && !isEntityToBeDeleted(cachedEntity)
                        && (cachedEntity instanceof AlwaysUpdatedPersistentObject
                        || cachedObject.hasChanged())) {

                    updatedObjects.add(cachedEntity);
                }
            }
        }
    }

    protected void removeUnnecessaryOperations() {

        for (Class<? extends Entity> entityClass : deletedObjects.keySet()) {

            // Collect ids of deleted entities + remove duplicates
            Set<String> ids = new HashSet<>();
            Iterator<Entity> entitiesToDeleteIterator =
                    deletedObjects.get(entityClass).values().iterator();
            while (entitiesToDeleteIterator.hasNext()) {
                Entity entityToDelete = entitiesToDeleteIterator.next();
                if (!ids.contains(entityToDelete.getId())) {
                    ids.add(entityToDelete.getId());
                } else {
                    entitiesToDeleteIterator.remove(); // Removing duplicate deletes
                }
            }

            // Now we have the deleted ids, we can remove the inserted objects (as they cancel each other)
            for (String id : ids) {
                if (insertedObjects.containsKey(entityClass)
                        && insertedObjects.get(entityClass).containsKey(id)) {
                    insertedObjects.get(entityClass).remove(id);
                    deletedObjects.get(entityClass).remove(id);
                }
            }
        }
    }

    protected void debugFlush() {
        LOGGER.debug("Flushing dbSqlSession");
        int nrOfInserts = 0;
        int nrOfUpdates = 0;
        int nrOfDeletes = 0;
        for (Map<String, Entity> insertedObjectMap : insertedObjects.values()) {
            for (Entity insertedObject : insertedObjectMap.values()) {
                LOGGER.debug("  insert {}", insertedObject);
                nrOfInserts++;
            }
        }
        for (Entity updatedObject : updatedObjects) {
            LOGGER.debug("  update {}", updatedObject);
            nrOfUpdates++;
        }
        for (Map<String, Entity> deletedObjectMap : deletedObjects.values()) {
            for (Entity deletedObject : deletedObjectMap.values()) {
                LOGGER.debug("  delete {} with id {}", deletedObject, deletedObject.getId());
                nrOfDeletes++;
            }
        }
        LOGGER.debug(
                "flush summary: {} insert, {} update, {} delete.", nrOfInserts, nrOfUpdates, nrOfDeletes);
        LOGGER.debug("now executing flush...");
    }

    public boolean isEntityInserted(Entity entity) {
        return isEntityInserted(entity.getClass(), entity.getId());
    }

    public boolean isEntityInserted(Class<?> entityClass, String entityId) {
        return insertedObjects.containsKey(entityClass)
                && insertedObjects.get(entityClass).containsKey(entityId);
    }

    public boolean isEntityToBeDeleted(Entity entity) {
        return (deletedObjects.containsKey(entity.getClass())
                && deletedObjects.get(entity.getClass()).containsKey(entity.getId()))
                || entity.isDeleted();
    }

    /**
     * TODO: copied from DbSqlSession, could be extracted in a common place.
     */
    protected List cacheLoadOrStore(List<Object> loadedObjects) {
        if (loadedObjects.isEmpty()) {
            return loadedObjects;
        }
        if (!(loadedObjects.get(0) instanceof Entity)) {
            return loadedObjects;
        }

        List<Entity> filteredObjects = new ArrayList<>(loadedObjects.size());
        for (Object loadedObject : loadedObjects) {
            Entity cachedEntity = cacheLoadOrStore((Entity) loadedObject);
            filteredObjects.add(cachedEntity);
        }
        return filteredObjects;
    }

    /**
     * TODO: copied from DbSqlSession, could be extracted in a common place.,
     *
     * <p>Returns the object in the cache. If this object was loaded before, then the original object
     * is returned (the cached version is more recent). If this is the first time this object is
     * loaded, then the loadedObject is added to the cache.
     */
    protected Entity cacheLoadOrStore(Entity entity) {
        Entity cachedEntity = entityCache.findInCache(entity.getClass(), entity.getId());
        if (cachedEntity != null) {
            return cachedEntity;
        }
        entityCache.put(entity, true);
        return entity;
    }

    protected MongoCollection<Document> getCollection(String collection) {
        return getMongoDatabase().getCollection(collection);
    }

    public void setMongoDbSessionFactory(MongoDbSessionFactory mongoDbSessionFactory) {
        this.mongoDbSessionFactory = mongoDbSessionFactory;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void setMongoDatabase(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public void setClientSession(ClientSession clientSession) {
        this.clientSession = clientSession;
    }

    public void setEntityCache(EntityCache entityCache) {
        this.entityCache = entityCache;
    }
}
