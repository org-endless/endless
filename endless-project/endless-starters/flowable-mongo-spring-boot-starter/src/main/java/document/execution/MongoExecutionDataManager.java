package document.execution;

import document.AbstractMongoDataManager;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.impl.ExecutionQueryImpl;
import org.flowable.engine.impl.ProcessInstanceQueryImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.engine.impl.persistence.entity.data.ExecutionDataManager;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * MongoExecutionDataManager
 * <p>
 * <p>
 * <p>create 2023/9/21 10:53
 * <p>update 2023/9/21 10:53
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
@Import({MongoExecutionRepository.class})
public class MongoExecutionDataManager extends AbstractMongoDataManager<ExecutionEntity>
        implements ExecutionDataManager {

    public static final String COLLECTION_EXECUTION = "ACT_RU_EXECUTION";

    private final MongoExecutionRepository repository;

    public MongoExecutionDataManager(MongoOperations operations, MongoExecutionRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_EXECUTION;
    }

    @Override
    public Class<ExecutionEntity> getEntityClass() {
        return ExecutionEntity.class;
    }

    @Override
    public Update getUpdateObject(ExecutionEntity entity) {
        ExecutionEntityImpl entityImpl = (ExecutionEntityImpl) entity;
        return Update.update("REV_", entityImpl.getRevision())
                .set("PROC_INST_ID_", entityImpl.getProcessInstanceId())
                .set("BUSINESS_KEY_", entityImpl.getBusinessKey())
                .set("PARENT_ID_", entityImpl.getParentId())
                .set("PROC_DEF_ID_", entityImpl.getProcessDefinitionId())
                .set("SUPER_EXEC_", entityImpl.getSuperExecutionId())
                .set("ROOT_PROC_INST_ID_", entityImpl.getRootProcessInstanceId())
                .set("ACT_ID_", entityImpl.getActivityId())
                .set("IS_ACTIVE_", entityImpl.isActive())
                .set("IS_CONCURRENT_", entityImpl.isConcurrent())
                .set("IS_SCOPE_", entityImpl.isScope())
                .set("IS_EVENT_SCOPE_", entityImpl.isEventScope())
                .set("IS_MI_ROOT_", entityImpl.isMultiInstanceRoot())
                .set("SUSPENSION_STATE_", entityImpl.getSuspensionState())
                .set("CACHED_ENT_STATE_", null)
                .set("TENANT_ID_", entityImpl.getTenantId())
                .set("NAME_", entityImpl.getName())
                .set("START_ACT_ID_", entityImpl.getStartUserId())
                .set("START_TIME_", entityImpl.getStartTime())
                .set("START_USER_ID_", entityImpl.getStartUserId())
                .set("LOCK_TIME_", entityImpl.getLockTime())
                .set("LOCK_OWNER_", entityImpl.getLockOwner())
                .set("IS_COUNT_ENABLED_", entityImpl.isCountEnabled())
                .set("EVT_SUBSCR_COUNT_", entityImpl.getEventSubscriptionCount())
                .set("TASK_COUNT_", entityImpl.getTaskCount())
                .set("JOB_COUNT_", entityImpl.getJobCount())
                .set("TIMER_JOB_COUNT_", entityImpl.getTimerJobCount())
                .set("SUSP_JOB_COUNT_", entityImpl.getSuspendedJobCount())
                .set("DEADLETTER_JOB_COUNT_", entityImpl.getDeadLetterJobCount())
                .set("EXTERNAL_WORKER_JOB_COUNT_", entityImpl.getExternalWorkerJobCount())
                .set("VAR_COUNT_", entityImpl.getVariableCount())
                .set("ID_LINK_COUNT_", entityImpl.getIdentityLinkCount())
                .set("CALLBACK_ID_", entityImpl.getCallbackId())
                .set("CALLBACK_TYPE_", entityImpl.getCallbackType())
                .set("REFERENCE_ID_", entityImpl.getReferenceId())
                .set("REFERENCE_TYPE_", entityImpl.getReferenceType())
                .set("PROPAGATED_STAGE_INST_ID_", entityImpl.getPropagatedStageInstanceId())
                .set("BUSINESS_STATUS_", entityImpl.getBusinessStatus());
    }

    @Override
    public ExecutionEntity findSubProcessInstanceBySuperExecutionId(String superExecutionId) {
        return repository.findBySuperExecutionId(superExecutionId);
    }

    @Override
    public List<ExecutionEntity> findChildExecutionsByParentExecutionId(String parentExecutionId) {
        return repository.findByParentId(parentExecutionId);
    }

    @Override
    public List<ExecutionEntity> findChildExecutionsByProcessInstanceId(String processInstanceId) {
        return repository.findByProcessInstanceIdAndParentIdIsNotNull(processInstanceId);
    }

    @Override
    public List<ExecutionEntity> findExecutionsByParentExecutionAndActivityIds(String parentExecutionId, Collection<String> activityIds) {
        return repository.findByParentIdAndActivityIdIn(parentExecutionId, activityIds);
    }

    @Override
    public long findExecutionCountByQueryCriteria(ExecutionQueryImpl executionQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ExecutionEntity> findExecutionsByQueryCriteria(ExecutionQueryImpl executionQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public long findProcessInstanceCountByQueryCriteria(ProcessInstanceQueryImpl executionQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ProcessInstance> findProcessInstanceByQueryCriteria(ProcessInstanceQueryImpl executionQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ExecutionEntity> findExecutionsByRootProcessInstanceId(String rootProcessInstanceId) {
        return repository.findByRootProcessInstanceId(rootProcessInstanceId);
    }

    @Override
    public List<ExecutionEntity> findExecutionsByProcessInstanceId(String processInstanceId) {
        return repository.findByProcessInstanceId(processInstanceId);
    }

    @Override
    public List<ProcessInstance> findProcessInstanceAndVariablesByQueryCriteria(ProcessInstanceQueryImpl executionQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<ExecutionEntity> findInactiveExecutionsByProcessInstanceId(String processInstanceId) {
        return repository.findByProcessInstanceIdAndIsActive(processInstanceId, false);
    }

    @Override
    public Collection<ExecutionEntity> findInactiveExecutionsByActivityIdAndProcessInstanceId(String activityId, String processInstanceId) {
        return repository.findByActivityIdAndProcessInstanceIdAndIsActive(activityId, processInstanceId, false);
    }

    @Override
    public List<String> findProcessInstanceIdsByProcessDefinitionId(String processDefinitionId) {
        return repository.findByProcessDefinitionIdAndParentIdIsNull(processDefinitionId);
    }

    @Override
    public List<Execution> findExecutionsByNativeQuery(Map<String, Object> parameterMap) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ProcessInstance> findProcessInstanceByNativeQuery(Map<String, Object> parameterMap) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public long findExecutionCountByNativeQuery(Map<String, Object> parameterMap) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public long countActiveExecutionsByParentId(String parentId) {
        return repository.countByParentIdAndIsActive(parentId, true);
    }

    @Override
    public void updateExecutionTenantIdForDeployment(String deploymentId, String newTenantId) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAllExecutionRelatedEntityCountFlags(boolean newValue) {
        var query = Query.query(Criteria.where("id").exists(true));
        var update = Update.update("IS_COUNT_ENABLED_", newValue);
        update(query, update);
    }

    @Override
    public void updateProcessInstanceLockTime(String processInstanceId, Date lockDate, String lockOwner, Date expirationTime) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearProcessInstanceLockTime(String processInstanceId) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearAllProcessInstanceLockTimes(String lockOwner) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public ExecutionEntityImpl create() {
        return new ExecutionEntityImpl();
    }
}
