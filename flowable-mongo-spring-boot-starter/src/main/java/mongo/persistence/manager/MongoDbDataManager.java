package mongo.persistence.manager;

import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * MongoDbDataManager
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/25 12:12
 * <p>update 2023/8/25 12:12
 *
 * @author Deng Haozhi
 * @since 0.1.1
 */
public interface MongoDbDataManager<EntityImpl extends Entity> {

    default Query getQueryObjectById(EntityImpl entity) {
        return Query.query(Criteria.where("ID_").is(entity.getId()));
    }

    default Query getQueryObjectById(String entityId) {
        return Query.query(Criteria.where("ID_").is(entityId));
    }

    Update getUpdateObject(EntityImpl entity);
}
