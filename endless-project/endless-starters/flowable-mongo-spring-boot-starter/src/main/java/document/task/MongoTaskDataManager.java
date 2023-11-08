package document.task;

import document.AbstractMongoDataManager;
import org.flowable.task.api.Task;
import org.flowable.task.service.impl.TaskQueryImpl;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.flowable.task.service.impl.persistence.entity.data.TaskDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;

/**
 * MongoTaskDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:37
 * <p>update 2023/9/22 16:37
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoTaskDataManager extends AbstractMongoDataManager<TaskEntity>
        implements TaskDataManager {

    public static final String COLLECTION_TASK = "ACT_RU_TASK";

    public MongoTaskDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_TASK;
    }

    @Override
    public Class<TaskEntity> getEntityClass() {
        return TaskEntity.class;
    }

    @Override
    public Update getUpdateObject(TaskEntity entity) {
        return null;
    }

    @Override
    public List<TaskEntity> findTasksByExecutionId(String executionId) {
        return null;
    }

    @Override
    public List<TaskEntity> findTasksByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<TaskEntity> findTasksByScopeIdAndScopeType(String scopeId, String scopeType) {
        return null;
    }

    @Override
    public List<TaskEntity> findTasksBySubScopeIdAndScopeType(String subScopeId, String scopeType) {
        return null;
    }

    @Override
    public List<Task> findTasksByQueryCriteria(TaskQueryImpl taskQuery) {
        return null;
    }

    @Override
    public List<Task> findTasksWithRelatedEntitiesByQueryCriteria(TaskQueryImpl taskQuery) {
        return null;
    }

    @Override
    public long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery) {
        return 0;
    }

    @Override
    public List<Task> findTasksByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findTaskCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public List<Task> findTasksByParentTaskId(String parentTaskId) {
        return null;
    }

    @Override
    public void updateTaskTenantIdForDeployment(String deploymentId, String newTenantId) {

    }

    @Override
    public void updateAllTaskRelatedEntityCountFlags(boolean newValue) {

    }

    @Override
    public void deleteTasksByExecutionId(String executionId) {

    }

    @Override
    public TaskEntity create() {
        return null;
    }
}
