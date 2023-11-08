package document.historic.variable.instance;

import document.AbstractMongoDataManager;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.flowable.variable.service.impl.HistoricVariableInstanceQueryImpl;
import org.flowable.variable.service.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.flowable.variable.service.impl.persistence.entity.data.HistoricVariableInstanceDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MongoHistoricVariableInstanceDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:33
 * <p>update 2023/9/22 16:33
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoHistoricVariableInstanceDataManager extends AbstractMongoDataManager<HistoricVariableInstanceEntity>
        implements HistoricVariableInstanceDataManager {

    public static final String COLLECTION_HISTORIC_VARIABLE_INSTANCE = "ACT_HI_VARINST";


    public MongoHistoricVariableInstanceDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_VARIABLE_INSTANCE;
    }

    @Override
    public Class<HistoricVariableInstanceEntity> getEntityClass() {
        return HistoricVariableInstanceEntity.class;
    }

    @Override
    public Update getUpdateObject(HistoricVariableInstanceEntity entity) {
        return null;
    }

    @Override
    public List<HistoricVariableInstanceEntity> findHistoricVariableInstancesByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<HistoricVariableInstanceEntity> findHistoricVariableInstancesByTaskId(String taskId) {
        return null;
    }

    @Override
    public long findHistoricVariableInstanceCountByQueryCriteria(HistoricVariableInstanceQueryImpl historicProcessVariableQuery) {
        return 0;
    }

    @Override
    public List<HistoricVariableInstance> findHistoricVariableInstancesByQueryCriteria(HistoricVariableInstanceQueryImpl historicProcessVariableQuery) {
        return null;
    }

    @Override
    public HistoricVariableInstanceEntity findHistoricVariableInstanceByVariableInstanceId(String variableInstanceId) {
        return null;
    }

    @Override
    public List<HistoricVariableInstanceEntity> findHistoricalVariableInstancesByScopeIdAndScopeType(String scopeId, String scopeType) {
        return null;
    }

    @Override
    public List<HistoricVariableInstanceEntity> findHistoricalVariableInstancesBySubScopeIdAndScopeType(String subScopeId, String scopeType) {
        return null;
    }

    @Override
    public List<HistoricVariableInstance> findHistoricVariableInstancesByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findHistoricVariableInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public void bulkDeleteHistoricVariableInstancesByProcessInstanceIds(Collection<String> processInstanceIds) {

    }

    @Override
    public void bulkDeleteHistoricVariableInstancesByTaskIds(Collection<String> taskIds) {

    }

    @Override
    public void bulkDeleteHistoricVariableInstancesByScopeIdsAndScopeType(Collection<String> scopeIds, String scopeType) {

    }

    @Override
    public void deleteHistoricVariableInstancesForNonExistingProcessInstances() {

    }

    @Override
    public void deleteHistoricVariableInstancesForNonExistingCaseInstances() {

    }

    @Override
    public HistoricVariableInstanceEntity create() {
        return null;
    }
}
