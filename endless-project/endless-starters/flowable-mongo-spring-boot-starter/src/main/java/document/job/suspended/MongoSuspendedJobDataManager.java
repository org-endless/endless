package document.job.suspended;

import document.AbstractMongoDataManager;
import org.flowable.job.api.Job;
import org.flowable.job.service.impl.SuspendedJobQueryImpl;
import org.flowable.job.service.impl.persistence.entity.SuspendedJobEntity;
import org.flowable.job.service.impl.persistence.entity.data.SuspendedJobDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * MongoSuspendedJobDataManager
 * <p>
 * <p>create 2023/9/26 15:58
 * <p>update 2023/9/26 15:58
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoSuspendedJobDataManager extends AbstractMongoDataManager<SuspendedJobEntity>
        implements SuspendedJobDataManager {
    public MongoSuspendedJobDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public SuspendedJobEntity findJobByCorrelationId(String correlationId) {
        return null;
    }

    @Override
    public List<SuspendedJobEntity> findJobsByExecutionId(String executionId) {
        return null;
    }

    @Override
    public List<SuspendedJobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<Job> findJobsByQueryCriteria(SuspendedJobQueryImpl jobQuery) {
        return null;
    }

    @Override
    public long findJobCountByQueryCriteria(SuspendedJobQueryImpl jobQuery) {
        return 0;
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {

    }

    @Override
    public String getCollection() {
        return null;
    }

    @Override
    public Class<SuspendedJobEntity> getEntityClass() {
        return null;
    }

    @Override
    public Update getUpdateObject(SuspendedJobEntity entity) {
        return null;
    }

    @Override
    public SuspendedJobEntity create() {
        return null;
    }
}
