package document.job.history;

import document.AbstractMongoDataManager;
import org.flowable.common.engine.impl.Page;
import org.flowable.job.api.HistoryJob;
import org.flowable.job.service.impl.HistoryJobQueryImpl;
import org.flowable.job.service.impl.persistence.entity.HistoryJobEntity;
import org.flowable.job.service.impl.persistence.entity.data.HistoryJobDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

/**
 * MongoHistoryJobDataManager
 * <p>
 * <p>create 2023/9/26 17:31
 * <p>update 2023/9/26 17:31
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoHistoryJobDataManager extends AbstractMongoDataManager<HistoryJobEntity>
        implements HistoryJobDataManager {
    public MongoHistoryJobDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public List<HistoryJob> findHistoryJobsByQueryCriteria(HistoryJobQueryImpl query) {
        return null;
    }

    @Override
    public long findHistoryJobCountByQueryCriteria(HistoryJobQueryImpl query) {
        return 0;
    }

    @Override
    public List<HistoryJobEntity> findJobsToExecute(List<String> enabledCategories, Page page) {
        return null;
    }

    @Override
    public List<HistoryJobEntity> findJobsByExecutionId(String executionId) {
        return null;
    }

    @Override
    public List<HistoryJobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<HistoryJobEntity> findExpiredJobs(List<String> enabledCategories, Page page) {
        return null;
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {

    }

    @Override
    public void bulkUpdateJobLockWithoutRevisionCheck(List<HistoryJobEntity> jobEntities, String lockOwner, Date lockExpirationTime) {

    }

    @Override
    public void resetExpiredJob(String jobId) {

    }

    @Override
    public String getCollection() {
        return null;
    }

    @Override
    public Class<HistoryJobEntity> getEntityClass() {
        return null;
    }

    @Override
    public Update getUpdateObject(HistoryJobEntity entity) {
        return null;
    }

    @Override
    public HistoryJobEntity create() {
        return null;
    }
}
