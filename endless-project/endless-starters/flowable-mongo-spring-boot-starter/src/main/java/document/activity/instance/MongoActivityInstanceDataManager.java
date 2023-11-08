package document.activity.instance;

import document.AbstractMongoDataManager;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.impl.ActivityInstanceQueryImpl;
import org.flowable.engine.impl.persistence.entity.ActivityInstanceEntity;
import org.flowable.engine.impl.persistence.entity.ActivityInstanceEntityImpl;
import org.flowable.engine.impl.persistence.entity.data.ActivityInstanceDataManager;
import org.flowable.engine.runtime.ActivityInstance;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * MongoActivityInstanceDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:15
 * <p>update 2023/9/22 16:15
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
@Import({MongoActivityInstanceRepository.class})
public class MongoActivityInstanceDataManager extends AbstractMongoDataManager<ActivityInstanceEntity>
        implements ActivityInstanceDataManager {

    public static final String COLLECTION_ACTIVITY_INSTANCE = "ACT_RU_ACTINST";

    private final MongoActivityInstanceRepository repository;

    public MongoActivityInstanceDataManager(MongoOperations operations, MongoActivityInstanceRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_ACTIVITY_INSTANCE;
    }

    @Override
    public Class<ActivityInstanceEntity> getEntityClass() {
        return ActivityInstanceEntity.class;
    }

    @Override
    public Update getUpdateObject(ActivityInstanceEntity entity) {
        return Update.update("REV_", entity.getRevision())
                .set("PROC_DEF_ID_", entity.getProcessDefinitionId())
                .set("PROC_INST_ID_", entity.getProcessInstanceId())
                .set("EXECUTION_ID_", entity.getExecutionId())
                .set("ACT_ID_", entity.getActivityId())
                .set("TASK_ID_", entity.getTaskId())
                .set("CALL_PROC_INST_ID_", entity.getCalledProcessInstanceId())
                .set("ACT_NAME_", entity.getActivityName())
                .set("ACT_TYPE_", entity.getActivityType())
                .set("ASSIGNEE_", entity.getAssignee())
                .set("START_TIME_", entity.getStartTime())
                .set("END_TIME_", entity.getEndTime())
                .set("DURATION_", entity.getDurationInMillis())
                .set("TRANSACTION_ORDER_", entity.getTransactionOrder())
                .set("DELETE_REASON_", entity.getDeleteReason())
                .set("TENANT_ID_", entity.getTenantId());
    }

    @Override
    public List<ActivityInstanceEntity> findUnfinishedActivityInstancesByExecutionAndActivityId(String executionId, String activityId) {
        return repository.findByExecutionIdAndActivityIdAndEndTime(executionId, activityId, null);
    }

    @Override
    public List<ActivityInstanceEntity> findActivityInstancesByExecutionIdAndActivityId(String executionId, String activityId) {
        return repository.findByExecutionIdAndActivityId(executionId, activityId);
    }

    @Override
    public ActivityInstanceEntity findActivityInstanceByTaskId(String taskId) {
        return repository.findByTaskId(taskId);
    }

    @Override
    public List<ActivityInstanceEntity> findActivityInstancesByProcessInstanceId(String processInstanceId, boolean includeDeleted) {
        // TODO includeDeleted
        var activityInstances = repository.findByProcessInstanceId(processInstanceId);
        activityInstances.sort(Comparator.comparing(ActivityInstanceEntity::getStartTime)
                .thenComparing(ActivityInstanceEntity::getTransactionOrder, Comparator.nullsFirst(Comparator.naturalOrder())));
        return activityInstances;
    }

    @Override
    public void deleteActivityInstancesByProcessInstanceId(String processInstanceId) {
        var query = Query.query(Criteria.where("PROC_INST_ID_").is(processInstanceId));
        delete(query);
    }

    @Override
    public long findActivityInstanceCountByQueryCriteria(ActivityInstanceQueryImpl activityInstanceQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ActivityInstance> findActivityInstancesByQueryCriteria(ActivityInstanceQueryImpl activityInstanceQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ActivityInstance> findActivityInstancesByNativeQuery(Map<String, Object> parameterMap) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public long findActivityInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public ActivityInstanceEntity create() {
        return new ActivityInstanceEntityImpl();
    }
}
