package document.deployment;

import document.AbstractMongoDataManager;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.impl.DeploymentQueryImpl;
import org.flowable.engine.impl.persistence.entity.DeploymentEntity;
import org.flowable.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.flowable.engine.impl.persistence.entity.data.DeploymentDataManager;
import org.flowable.engine.repository.Deployment;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;

/**
 * MongoDeploymentDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:30
 * <p>update 2023/9/22 16:30
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
@Import({MongoDeploymentRepository.class})
public class MongoDeploymentDataManager extends AbstractMongoDataManager<DeploymentEntity>
        implements DeploymentDataManager {

    public static final String COLLECTION_DEPLOYMENT = "ACT_RE_DEPLOYMENT";

    private final MongoDeploymentRepository repository;

    public MongoDeploymentDataManager(MongoOperations operations, MongoDeploymentRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_DEPLOYMENT;
    }

    @Override
    public Class<DeploymentEntity> getEntityClass() {
        return DeploymentEntity.class;
    }

    @Override
    public Update getUpdateObject(DeploymentEntity entity) {
        return Update.update("NAME_", entity.getName())
                .set("CATEGORY_", entity.getCategory())
                .set("KEY_", entity.getKey())
                .set("TENANT_ID_", entity.getTenantId())
                .set("DEPLOY_TIME_", entity.getDeploymentTime())
                .set("DERIVED_FROM_", entity.getDerivedFrom())
                .set("DERIVED_FROM_ROOT_", entity.getDerivedFromRoot())
                .set("PARENT_DEPLOYMENT_ID_", entity.getParentDeploymentId())
                .set("ENGINE_VERSION_", entity.getEngineVersion());
    }

    @Override
    public long findDeploymentCountByQueryCriteria(DeploymentQueryImpl deploymentQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Deployment> findDeploymentsByQueryCriteria(DeploymentQueryImpl deploymentQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getDeploymentResourceNames(String deploymentId) {
        // TODO Resource
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Deployment> findDeploymentsByNativeQuery(Map<String, Object> parameterMap) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public long findDeploymentCountByNativeQuery(Map<String, Object> parameterMap) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public DeploymentEntity create() {
        return new DeploymentEntityImpl();
    }
}
