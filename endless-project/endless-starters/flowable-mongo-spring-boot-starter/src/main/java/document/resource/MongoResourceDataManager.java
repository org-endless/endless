package document.resource;

import document.AbstractMongoDataManager;
import org.flowable.engine.impl.persistence.entity.ResourceEntity;
import org.flowable.engine.impl.persistence.entity.data.ResourceDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * MongoResourceDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:36
 * <p>update 2023/9/22 16:36
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoResourceDataManager extends AbstractMongoDataManager<ResourceEntity>
        implements ResourceDataManager {

    public static final String COLLECTION_RESOURCE = "ACT_GE_BYTEARRAY";

    public MongoResourceDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_RESOURCE;
    }

    @Override
    public Class<ResourceEntity> getEntityClass() {
        return ResourceEntity.class;
    }

    @Override
    public Update getUpdateObject(ResourceEntity entity) {
        return null;
    }

    @Override
    public void deleteResourcesByDeploymentId(String deploymentId) {

    }

    @Override
    public ResourceEntity findResourceByDeploymentIdAndResourceName(String deploymentId, String resourceName) {
        return null;
    }

    @Override
    public List<ResourceEntity> findResourcesByDeploymentId(String deploymentId) {
        return null;
    }

    @Override
    public ResourceEntity create() {
        return null;
    }
}
