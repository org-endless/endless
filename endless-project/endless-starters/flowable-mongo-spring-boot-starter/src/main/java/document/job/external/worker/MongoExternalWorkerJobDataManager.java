package document.job.external.worker;

import document.AbstractMongoDataManager;
import org.flowable.common.engine.impl.Page;
import org.flowable.job.api.ExternalWorkerJob;
import org.flowable.job.service.impl.ExternalWorkerJobAcquireBuilderImpl;
import org.flowable.job.service.impl.ExternalWorkerJobQueryImpl;
import org.flowable.job.service.impl.persistence.entity.ExternalWorkerJobEntity;
import org.flowable.job.service.impl.persistence.entity.data.ExternalWorkerJobDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

/**
 * MongoExternalWorkerJobDataManager
 * <p>
 * <p>create 2023/9/26 17:48
 * <p>update 2023/9/26 17:48
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoExternalWorkerJobDataManager extends AbstractMongoDataManager<ExternalWorkerJobEntity>
        implements ExternalWorkerJobDataManager {
    public MongoExternalWorkerJobDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public ExternalWorkerJobEntity findJobByCorrelationId(String correlationId) {
        return null;
    }

    @Override
    public List<ExternalWorkerJob> findJobsByQueryCriteria(ExternalWorkerJobQueryImpl jobQuery) {
        return null;
    }

    @Override
    public long findJobCountByQueryCriteria(ExternalWorkerJobQueryImpl jobQuery) {
        return 0;
    }

    @Override
    public void deleteJobsByExecutionId(String executionId) {

    }

    @Override
    public List<ExternalWorkerJobEntity> findExternalJobsToExecute(ExternalWorkerJobAcquireBuilderImpl builder, int numberOfJobs) {
        return null;
    }

    @Override
    public List<ExternalWorkerJobEntity> findJobsByScopeIdAndSubScopeId(String scopeId, String subScopeId) {
        return null;
    }

    @Override
    public List<ExternalWorkerJobEntity> findJobsByWorkerId(String workerId) {
        return null;
    }

    @Override
    public List<ExternalWorkerJobEntity> findJobsByWorkerIdAndTenantId(String workerId, String tenantId) {
        return null;
    }

    @Override
    public List<ExternalWorkerJobEntity> findJobsToExecute(List<String> enabledCategories, Page page) {
        return null;
    }

    @Override
    public List<ExternalWorkerJobEntity> findJobsByExecutionId(String executionId) {
        return null;
    }

    @Override
    public List<ExternalWorkerJobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<ExternalWorkerJobEntity> findExpiredJobs(List<String> enabledCategories, Page page) {
        return null;
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {

    }

    @Override
    public void bulkUpdateJobLockWithoutRevisionCheck(List<ExternalWorkerJobEntity> jobEntities, String lockOwner, Date lockExpirationTime) {

    }

    @Override
    public void resetExpiredJob(String jobId) {

    }

    @Override
    public String getCollection() {
        return null;
    }

    @Override
    public Class<ExternalWorkerJobEntity> getEntityClass() {
        return null;
    }

    @Override
    public Update getUpdateObject(ExternalWorkerJobEntity entity) {
        return null;
    }

    @Override
    public ExternalWorkerJobEntity create() {
        return null;
    }
}
