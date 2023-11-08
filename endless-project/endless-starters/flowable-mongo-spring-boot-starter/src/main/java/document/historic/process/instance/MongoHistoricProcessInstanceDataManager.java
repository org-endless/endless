package document.historic.process.instance;

import document.AbstractMongoDataManager;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.HistoricProcessInstanceQueryImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.flowable.engine.impl.persistence.entity.data.HistoricProcessInstanceDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MongoHistoricProcessInstanceDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:32
 * <p>update 2023/9/22 16:32
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoHistoricProcessInstanceDataManager extends AbstractMongoDataManager<HistoricProcessInstanceEntity>
        implements HistoricProcessInstanceDataManager {

    public static final String COLLECTION_HISTORIC_PROCESS_INSTANCE = "ACT_HI_PROCINST";


    public MongoHistoricProcessInstanceDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_PROCESS_INSTANCE;
    }

    @Override
    public Class<HistoricProcessInstanceEntity> getEntityClass() {
        return HistoricProcessInstanceEntity.class;
    }

    @Override
    public Update getUpdateObject(HistoricProcessInstanceEntity entity) {
        return null;
    }

    @Override
    public HistoricProcessInstanceEntity create(ExecutionEntity processInstanceExecutionEntity) {
        return null;
    }

    @Override
    public List<String> findHistoricProcessInstanceIdsByProcessDefinitionId(String processDefinitionId) {
        return null;
    }

    @Override
    public List<HistoricProcessInstance> findHistoricProcessInstancesBySuperProcessInstanceId(String superProcessInstanceId) {
        return null;
    }

    @Override
    public List<String> findHistoricProcessInstanceIdsBySuperProcessInstanceIds(Collection<String> superProcessInstanceIds) {
        return null;
    }

    @Override
    public long findHistoricProcessInstanceCountByQueryCriteria(HistoricProcessInstanceQueryImpl historicProcessInstanceQuery) {
        return 0;
    }

    @Override
    public List<HistoricProcessInstance> findHistoricProcessInstancesByQueryCriteria(HistoricProcessInstanceQueryImpl historicProcessInstanceQuery) {
        return null;
    }

    @Override
    public List<HistoricProcessInstance> findHistoricProcessInstancesAndVariablesByQueryCriteria(HistoricProcessInstanceQueryImpl historicProcessInstanceQuery) {
        return null;
    }

    @Override
    public List<HistoricProcessInstance> findHistoricProcessInstancesByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findHistoricProcessInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public void deleteHistoricProcessInstances(HistoricProcessInstanceQueryImpl historicProcessInstanceQuery) {

    }

    @Override
    public void bulkDeleteHistoricProcessInstances(Collection<String> processInstanceIds) {

    }

    @Override
    public HistoricProcessInstanceEntity create() {
        return null;
    }
}
