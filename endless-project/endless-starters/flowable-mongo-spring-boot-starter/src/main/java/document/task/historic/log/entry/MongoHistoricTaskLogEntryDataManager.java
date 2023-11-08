package document.task.historic.log.entry;

import document.AbstractMongoDataManager;
import org.flowable.task.api.history.HistoricTaskLogEntry;
import org.flowable.task.service.impl.HistoricTaskLogEntryQueryImpl;
import org.flowable.task.service.impl.persistence.entity.HistoricTaskLogEntryEntity;
import org.flowable.task.service.impl.persistence.entity.data.HistoricTaskLogEntryDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MongoHistoricTaskLogEntryDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:33
 * <p>update 2023/9/22 16:33
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoHistoricTaskLogEntryDataManager extends AbstractMongoDataManager<HistoricTaskLogEntryEntity>
        implements HistoricTaskLogEntryDataManager {

    public static final String COLLECTION_HISTORIC_TASK_INSTANCE = "ACT_HI_TASKINST";

    public MongoHistoricTaskLogEntryDataManager(MongoOperations operations) {
        super(operations);
    }


    @Override
    public void deleteHistoricTaskLogEntry(long logEntryNumber) {

    }

    @Override
    public long findHistoricTaskLogEntriesCountByQueryCriteria(HistoricTaskLogEntryQueryImpl taskLogEntryQuery) {
        return 0;
    }

    @Override
    public List<HistoricTaskLogEntry> findHistoricTaskLogEntriesByQueryCriteria(HistoricTaskLogEntryQueryImpl taskLogEntryQuery) {
        return null;
    }

    @Override
    public long findHistoricTaskLogEntriesCountByNativeQueryCriteria(Map<String, Object> nativeHistoricTaskLogEntryQuery) {
        return 0;
    }

    @Override
    public List<HistoricTaskLogEntry> findHistoricTaskLogEntriesByNativeQueryCriteria(Map<String, Object> nativeHistoricTaskLogEntryQuery) {
        return null;
    }

    @Override
    public void deleteHistoricTaskLogEntriesByProcessDefinitionId(String processDefinitionId) {

    }

    @Override
    public void deleteHistoricTaskLogEntriesByScopeDefinitionId(String scopeType, String scopeDefinitionId) {

    }

    @Override
    public void deleteHistoricTaskLogEntriesByTaskId(String taskId) {

    }

    @Override
    public void bulkDeleteHistoricTaskLogEntriesForTaskIds(Collection<String> taskIds) {

    }

    @Override
    public void deleteHistoricTaskLogEntriesForNonExistingProcessInstances() {

    }

    @Override
    public void deleteHistoricTaskLogEntriesForNonExistingCaseInstances() {

    }

    @Override
    public String getCollection() {
        return null;
    }

    @Override
    public Class<HistoricTaskLogEntryEntity> getEntityClass() {
        return null;
    }

    @Override
    public Update getUpdateObject(HistoricTaskLogEntryEntity entity) {
        return null;
    }

    @Override
    public HistoricTaskLogEntryEntity create() {
        return null;
    }
}
