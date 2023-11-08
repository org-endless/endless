package document.historic.detail;

import document.AbstractMongoDataManager;
import org.flowable.engine.history.HistoricDetail;
import org.flowable.engine.impl.HistoricDetailQueryImpl;
import org.flowable.engine.impl.persistence.entity.HistoricDetailAssignmentEntity;
import org.flowable.engine.impl.persistence.entity.HistoricDetailEntity;
import org.flowable.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;
import org.flowable.engine.impl.persistence.entity.HistoricFormPropertyEntity;
import org.flowable.engine.impl.persistence.entity.data.HistoricDetailDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MongoHistoricDetailDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:31
 * <p>update 2023/9/22 16:31
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoHistoricDetailDataManager extends AbstractMongoDataManager<HistoricDetailEntity>
        implements HistoricDetailDataManager {

    public static final String COLLECTION_HISTORIC_DETAIL = "ACT_HI_DETAIL";


    public MongoHistoricDetailDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_DETAIL;
    }

    @Override
    public Class<HistoricDetailEntity> getEntityClass() {
        return HistoricDetailEntity.class;
    }

    @Override
    public Update getUpdateObject(HistoricDetailEntity entity) {
        return null;
    }

    @Override
    public HistoricDetailAssignmentEntity createHistoricDetailAssignment() {
        return null;
    }

    @Override
    public HistoricDetailVariableInstanceUpdateEntity createHistoricDetailVariableInstanceUpdate() {
        return null;
    }

    @Override
    public HistoricFormPropertyEntity createHistoricFormProperty() {
        return null;
    }

    @Override
    public List<HistoricDetailEntity> findHistoricDetailsByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<HistoricDetailEntity> findHistoricDetailsByTaskId(String taskId) {
        return null;
    }

    @Override
    public long findHistoricDetailCountByQueryCriteria(HistoricDetailQueryImpl historicVariableUpdateQuery) {
        return 0;
    }

    @Override
    public List<HistoricDetail> findHistoricDetailsByQueryCriteria(HistoricDetailQueryImpl historicVariableUpdateQuery) {
        return null;
    }

    @Override
    public List<HistoricDetail> findHistoricDetailsByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findHistoricDetailCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public void bulkDeleteHistoricDetailsByProcessInstanceIds(Collection<String> historicProcessInstanceIds) {

    }

    @Override
    public void bulkDeleteHistoricDetailsByTaskIds(Collection<String> taskIds) {

    }

    @Override
    public void deleteHistoricDetailForNonExistingProcessInstances() {

    }

    @Override
    public HistoricDetailEntity create() {
        return null;
    }
}
