package mongo.document.timer.job;

import mongo.persistence.manager.MongoDbDataManager;
import org.flowable.common.engine.impl.Page;
import org.flowable.job.api.Job;
import org.flowable.job.service.impl.TimerJobQueryImpl;
import org.flowable.job.service.impl.persistence.entity.TimerJobEntity;
import org.flowable.job.service.impl.persistence.entity.data.TimerJobDataManager;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

/**
 * MongoTimerJobDataManager
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/27 11:51
 * <p>update 2023/8/27 11:51
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoTimerJobDataManager
        implements MongoDbDataManager<TimerJobEntity>, TimerJobDataManager {


    @Override
    public Update getUpdateObject(TimerJobEntity entity) {
        return Update.update("REV_", entity.getRevision())
                .set("CATEGORY_", entity.getCategory())
                .set("TYPE_", entity.getJobType())
                .set("LOCK_EXP_TIME_", entity.getLockExpirationTime())
                .set("LOCK_OWNER_", entity.getLockOwner())
                .set("EXCLUSIVE_", entity.isExclusive())
                .set("EXECUTION_ID_", entity.getExecutionId())
                .set("PROC_INST_ID_", entity.getProcessInstanceId())
                .set("PROC_DEF_ID_", entity.getProcessDefinitionId())
                .set("ELEMENT_ID_", entity.getElementId())
                .set("ELEMENT_NAME_", entity.getElementName())
                .set("SCOPE_ID_", entity.getScopeId())
                .set("SUB_SCOPE_ID_", entity.getSubScopeId())
                .set("SCOPE_TYPE_", entity.getScopeType())
                .set("SCOPE_DEFINITION_ID_", entity.getScopeDefinitionId())
                .set("CORRELATION_ID_", entity.getCorrelationId())
                .set("RETRIES_", entity.getRetries())
                .set("EXCEPTION_STACK_ID_", entity.getExceptionByteArrayRef())
                .set("EXCEPTION_MSG_", entity.getExceptionMessage())
                .set("DUEDATE_", entity.getDuedate())
                .set("REPEAT_", entity.getRepeat())
                .set("HANDLER_TYPE_", entity.getJobHandlerType())
                .set("HANDLER_CFG_", entity.getJobHandlerConfiguration())
                .set("CUSTOM_VALUES_ID_", entity.getCustomValuesByteArrayRef())
                .set("CREATE_TIME_", entity.getCreateTime())
                .set("TENANT_ID_", entity.getTenantId());
    }

    @Override
    public TimerJobEntity findJobByCorrelationId(String correlationId) {
        return null;
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionId(String jobHandlerType, String processDefinitionId) {
        return null;
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionKeyNoTenantId(String jobHandlerType, String processDefinitionKey) {
        return null;
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionKeyAndTenantId(String jobHandlerType, String processDefinitionKey, String tenantId) {
        return null;
    }

    @Override
    public List<TimerJobEntity> findJobsByScopeIdAndSubScopeId(String scopeId, String subScopeId) {
        return null;
    }

    @Override
    public List<Job> findJobsByQueryCriteria(TimerJobQueryImpl jobQuery) {
        return null;
    }

    @Override
    public long findJobCountByQueryCriteria(TimerJobQueryImpl jobQuery) {
        return 0;
    }

    @Override
    public void bulkDeleteWithoutRevision(List<TimerJobEntity> timerJobEntities) {

    }

    @Override
    public List<TimerJobEntity> findJobsToExecute(List<String> enabledCategories, Page page) {
        return null;
    }

    @Override
    public List<TimerJobEntity> findJobsByExecutionId(String executionId) {
        return null;
    }

    @Override
    public List<TimerJobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<TimerJobEntity> findExpiredJobs(List<String> enabledCategories, Page page) {
        return null;
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {

    }

    @Override
    public void bulkUpdateJobLockWithoutRevisionCheck(List<TimerJobEntity> jobEntities, String lockOwner, Date lockExpirationTime) {

    }

    @Override
    public void resetExpiredJob(String jobId) {

    }

    @Override
    public TimerJobEntity create() {
        return null;
    }

    @Override
    public TimerJobEntity findById(String entityId) {
        return null;
    }

    @Override
    public void insert(TimerJobEntity entity) {

    }

    @Override
    public TimerJobEntity update(TimerJobEntity entity) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void delete(TimerJobEntity entity) {

    }


}
