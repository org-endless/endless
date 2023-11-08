package document.job;

import document.AbstractMongoDataManager;
import org.flowable.common.engine.impl.Page;
import org.flowable.job.api.Job;
import org.flowable.job.service.impl.JobQueryImpl;
import org.flowable.job.service.impl.persistence.entity.JobEntity;
import org.flowable.job.service.impl.persistence.entity.data.JobDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

/**
 * MongoJobDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:35
 * <p>update 2023/9/22 16:35
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoJobDataManager extends AbstractMongoDataManager<JobEntity>
        implements JobDataManager {

    public static final String COLLECTION_JOB = "ACT_RU_JOB";

    public MongoJobDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_JOB;
    }

    @Override
    public Class<JobEntity> getEntityClass() {
        return JobEntity.class;
    }

    @Override
    public Update getUpdateObject(JobEntity entity) {
        return null;
    }

    @Override
    public JobEntity findJobByCorrelationId(String correlationId) {
        return null;
    }

    @Override
    public List<Job> findJobsByQueryCriteria(JobQueryImpl jobQuery) {
        return null;
    }

    @Override
    public long findJobCountByQueryCriteria(JobQueryImpl jobQuery) {
        return 0;
    }

    @Override
    public void deleteJobsByExecutionId(String executionId) {

    }

    @Override
    public List<JobEntity> findJobsToExecute(List<String> enabledCategories, Page page) {
        return null;
    }

    @Override
    public List<JobEntity> findJobsByExecutionId(String executionId) {
        return null;
    }

    @Override
    public List<JobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<JobEntity> findExpiredJobs(List<String> enabledCategories, Page page) {
        return null;
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {

    }

    @Override
    public void bulkUpdateJobLockWithoutRevisionCheck(List<JobEntity> jobEntities, String lockOwner, Date lockExpirationTime) {

    }

    @Override
    public void resetExpiredJob(String jobId) {

    }

    @Override
    public JobEntity create() {
        return null;
    }
}
