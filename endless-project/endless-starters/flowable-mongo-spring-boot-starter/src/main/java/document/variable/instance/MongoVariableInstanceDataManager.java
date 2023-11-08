package document.variable.instance;

import document.AbstractMongoDataManager;
import org.flowable.variable.api.persistence.entity.VariableInstance;
import org.flowable.variable.service.impl.InternalVariableInstanceQueryImpl;
import org.flowable.variable.service.impl.VariableInstanceQueryImpl;
import org.flowable.variable.service.impl.persistence.entity.VariableInstanceEntity;
import org.flowable.variable.service.impl.persistence.entity.VariableInstanceEntityImpl;
import org.flowable.variable.service.impl.persistence.entity.data.VariableInstanceDataManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * MongoVariableInstanceDataManager
 * <p>
 * <p>create 2023/9/26 15:51
 * <p>update 2023/9/26 15:51
 *
 * @author Deng Haozhi
 * @see AbstractMongoDataManager
 * @see VariableInstanceDataManager
 * @since 0.0.4
 */
@Import(MongoVariableInstanceRepository.class)
public class MongoVariableInstanceDataManager extends AbstractMongoDataManager<VariableInstanceEntity>
        implements VariableInstanceDataManager {

    public static final String COLLECTION_VARIABLE_INSTANCE = "ACT_RU_VARIABLE";

    private final MongoVariableInstanceRepository repository;

    public MongoVariableInstanceDataManager(MongoOperations operations, MongoVariableInstanceRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_VARIABLE_INSTANCE;
    }

    @Override
    public Class<VariableInstanceEntity> getEntityClass() {
        return VariableInstanceEntity.class;
    }

    @Override
    public Update getUpdateObject(VariableInstanceEntity entity) {
        return Update.update("REV_", entity.getRevision())
                .set("TYPE_", entity.getType())
                .set("NAME_", entity.getName())
                .set("EXECUTION_ID_", entity.getExecutionId())
                .set("PROC_INST_ID_", entity.getProcessInstanceId())
                .set("TASK_ID_", entity.getTaskId())
                .set("SCOPE_ID_", entity.getScopeId())
                .set("SCOPE_TYPE_", entity.getScopeType())
                .set("BYTEARRAY_ID_", entity.getByteArrayRef())
                .set("DOUBLE_", entity.getDoubleValue())
                .set("LONG_", entity.getLongValue())
                .set("TEXT_", entity.getTextValue())
                .set("TEXT2_", entity.getTextValue2());
    }

    @Override
    public List<VariableInstanceEntity> findVariablesInstancesByQuery(InternalVariableInstanceQueryImpl internalVariableInstanceQuery) {
        return null;
    }

    @Override
    public VariableInstanceEntity findVariablesInstanceByQuery(InternalVariableInstanceQueryImpl internalVariableInstanceQuery) {
        return null;
    }

    @Override
    public long findVariableInstanceCountByQueryCriteria(VariableInstanceQueryImpl variableInstanceQuery) {
        return 0;
    }

    @Override
    public List<VariableInstance> findVariableInstancesByQueryCriteria(VariableInstanceQueryImpl variableInstanceQuery) {
        return null;
    }

    @Override
    public List<VariableInstance> findVariableInstancesByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findVariableInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public void deleteVariablesByTaskId(String taskId) {

    }

    @Override
    public void deleteVariablesByExecutionId(String executionId) {

    }

    @Override
    public void deleteByScopeIdAndScopeType(String scopeId, String scopeType) {

    }

    @Override
    public void deleteByScopeIdAndScopeTypes(String scopeId, Collection<String> scopeTypes) {

    }

    @Override
    public void deleteBySubScopeIdAndScopeTypes(String subScopeId, Collection<String> scopeTypes) {

    }

    @Override
    public VariableInstanceEntity create() {
        VariableInstanceEntityImpl variableInstanceEntity = new VariableInstanceEntityImpl();
        variableInstanceEntity.setRevision(0);
        return variableInstanceEntity;
    }
}
