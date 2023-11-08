package document.task.historic.instance;

import document.AbstractMongoDataManager;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.impl.HistoricTaskInstanceQueryImpl;
import org.flowable.task.service.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.flowable.task.service.impl.persistence.entity.data.HistoricTaskInstanceDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MongoHistoricTaskLogEntryDataManager
 * <p>
 * <p>
 * <p>
 * <p>create 2023/9/22 16:33
 * <p>update 2023/9/22 16:33
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoHistoricTaskInstanceDataManager extends AbstractMongoDataManager<HistoricTaskInstanceEntity>
        implements HistoricTaskInstanceDataManager {

    public static final String COLLECTION_HISTORIC_TASK_INSTANCE = "ACT_HI_TASKINST";


    public MongoHistoricTaskInstanceDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_TASK_INSTANCE;
    }

    @Override
    public Class<HistoricTaskInstanceEntity> getEntityClass() {
        return HistoricTaskInstanceEntity.class;
    }

    @Override
    public Update getUpdateObject(HistoricTaskInstanceEntity entity) {
        return null;
    }

    @Override
    public HistoricTaskInstanceEntity create(TaskEntity task) {
        return null;
    }

    @Override
    public List<HistoricTaskInstanceEntity> findHistoricTasksByParentTaskId(String parentTaskId) {
        return null;
    }

    @Override
    public List<String> findHistoricTaskIdsByParentTaskIds(Collection<String> parentTaskIds) {
        return null;
    }

    @Override
    public List<HistoricTaskInstanceEntity> findHistoricTasksByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<String> findHistoricTaskIdsForProcessInstanceIds(Collection<String> processInstanceIds) {
        return null;
    }

    @Override
    public List<String> findHistoricTaskIdsForScopeIdsAndScopeType(Collection<String> scopeIds, String scopeType) {
        return null;
    }

    @Override
    public long findHistoricTaskInstanceCountByQueryCriteria(HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        return 0;
    }

    @Override
    public List<HistoricTaskInstance> findHistoricTaskInstancesByQueryCriteria(HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        return null;
    }

    @Override
    public List<HistoricTaskInstance> findHistoricTaskInstancesAndRelatedEntitiesByQueryCriteria(HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        return null;
    }

    @Override
    public List<HistoricTaskInstance> findHistoricTaskInstancesByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findHistoricTaskInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public void deleteHistoricTaskInstances(HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {

    }

    @Override
    public void bulkDeleteHistoricTaskInstancesForIds(Collection<String> taskIds) {

    }

    @Override
    public void deleteHistoricTaskInstancesForNonExistingProcessInstances() {

    }

    @Override
    public void deleteHistoricTaskInstancesForNonExistingCaseInstances() {

    }

    @Override
    public HistoricTaskInstanceEntity create() {
        return null;
    }
}
