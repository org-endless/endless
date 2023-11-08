package document.historic.activity.instance;

import document.AbstractMongoDataManager;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.HistoricActivityInstanceQueryImpl;
import org.flowable.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.flowable.engine.impl.persistence.entity.data.HistoricActivityInstanceDataManager;
import org.flowable.engine.runtime.ActivityInstance;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MongoHistoricActivityInstanceDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:31
 * <p>update 2023/9/22 16:31
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoHistoricActivityInstanceDataManager extends AbstractMongoDataManager<HistoricActivityInstanceEntity>
        implements HistoricActivityInstanceDataManager {

    public static final String COLLECTION_HISTORIC_ACTIVITY_INSTANCE = "ACT_HI_ACTINST";

    private final MongoHistoricActivityInstanceRepository repository;

    public MongoHistoricActivityInstanceDataManager(MongoOperations operations, MongoHistoricActivityInstanceRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_ACTIVITY_INSTANCE;
    }

    @Override
    public Class<HistoricActivityInstanceEntity> getEntityClass() {
        return HistoricActivityInstanceEntity.class;
    }

    @Override
    public Update getUpdateObject(HistoricActivityInstanceEntity entity) {
        return null;
    }

    @Override
    public HistoricActivityInstanceEntity create(ActivityInstance activityInstance) {
        return null;
    }

    @Override
    public List<HistoricActivityInstanceEntity> findUnfinishedHistoricActivityInstancesByExecutionAndActivityId(String executionId, String activityId) {
        return null;
    }

    @Override
    public List<HistoricActivityInstanceEntity> findHistoricActivityInstancesByExecutionIdAndActivityId(String executionId, String activityId) {
        return null;
    }

    @Override
    public List<HistoricActivityInstanceEntity> findUnfinishedHistoricActivityInstancesByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public void deleteHistoricActivityInstancesByProcessInstanceId(String historicProcessInstanceId) {

    }

    @Override
    public long findHistoricActivityInstanceCountByQueryCriteria(HistoricActivityInstanceQueryImpl historicActivityInstanceQuery) {
        return 0;
    }

    @Override
    public List<HistoricActivityInstance> findHistoricActivityInstancesByQueryCriteria(HistoricActivityInstanceQueryImpl historicActivityInstanceQuery) {
        return null;
    }

    @Override
    public List<HistoricActivityInstance> findHistoricActivityInstancesByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findHistoricActivityInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public void deleteHistoricActivityInstances(HistoricActivityInstanceQueryImpl historicActivityInstanceQuery) {

    }

    @Override
    public void bulkDeleteHistoricActivityInstancesByProcessInstanceIds(Collection<String> historicProcessInstanceIds) {

    }

    @Override
    public void deleteHistoricActivityInstancesForNonExistingProcessInstances() {

    }

    @Override
    public HistoricActivityInstanceEntity create() {
        return null;
    }
}
