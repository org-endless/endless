package document;

import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.common.engine.impl.persistence.entity.data.DataManager;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * AbstractMongoDataManager
 * <p>
 * <p>
 * <p>
 * <p>create 2023/9/21 16:21
 * <p>update 2023/9/21 16:21
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public abstract class AbstractMongoDataManager<EntityImpl extends Entity> implements DataManager<EntityImpl> {

    private final MongoOperations operations;

    public AbstractMongoDataManager(MongoOperations operations) {
        this.operations = operations;
    }

    public abstract String getCollection();

    public abstract Class<EntityImpl> getEntityClass();

    public abstract Update getUpdateObject(EntityImpl entity);

    @Override
    public EntityImpl findById(String entityId) {
        return operations.findById(entityId, getEntityClass(), getCollection());
    }

    @Override
    public void insert(EntityImpl entity) {
        operations.insert(entity, getCollection());
    }

    @Override
    public EntityImpl update(EntityImpl entity) {
        return update(getQueryObjectById(entity), getUpdateObject(entity));
    }

    public EntityImpl update(Query query, Update update) {
        return operations.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), getEntityClass(), getCollection());
    }

    @Override
    public void delete(String id) {
        operations.remove(getQueryObjectById(id));
    }

    @Override
    public void delete(EntityImpl entity) {
        operations.remove(getQueryObjectById(entity));
    }

    public void delete(Query query) {
        operations.remove(query, getCollection());
    }

    public Query getQueryObjectById(EntityImpl entity) {
        return Query.query(Criteria.where("ID_").is(entity.getId()));
    }

    public Query getQueryObjectById(String entityId) {
        return Query.query(Criteria.where("ID_").is(entityId));
    }
}
