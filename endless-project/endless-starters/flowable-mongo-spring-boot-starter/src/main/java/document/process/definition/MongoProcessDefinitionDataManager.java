package document.process.definition;

import document.AbstractMongoDataManager;
import org.flowable.engine.impl.ProcessDefinitionQueryImpl;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.flowable.engine.impl.persistence.entity.data.ProcessDefinitionDataManager;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;

/**
 * MongoProcessDefinitionDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:36
 * <p>update 2023/9/22 16:36
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoProcessDefinitionDataManager extends AbstractMongoDataManager<ProcessDefinitionEntity>
        implements ProcessDefinitionDataManager {

    public static final String COLLECTION_PROCESS_DEFINITION = "ACT_RE_PROCDEF";


    public MongoProcessDefinitionDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_PROCESS_DEFINITION;
    }

    @Override
    public Class<ProcessDefinitionEntity> getEntityClass() {
        return ProcessDefinitionEntity.class;
    }

    @Override
    public Update getUpdateObject(ProcessDefinitionEntity entity) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findLatestProcessDefinitionByKey(String processDefinitionKey) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findLatestProcessDefinitionByKeyAndTenantId(String processDefinitionKey, String tenantId) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findLatestDerivedProcessDefinitionByKey(String processDefinitionKey) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findLatestDerivedProcessDefinitionByKeyAndTenantId(String processDefinitionKey, String tenantId) {
        return null;
    }

    @Override
    public void deleteProcessDefinitionsByDeploymentId(String deploymentId) {

    }

    @Override
    public List<ProcessDefinition> findProcessDefinitionsByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery) {
        return null;
    }

    @Override
    public long findProcessDefinitionCountByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery) {
        return 0;
    }

    @Override
    public ProcessDefinitionEntity findProcessDefinitionByDeploymentAndKey(String deploymentId, String processDefinitionKey) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findProcessDefinitionByDeploymentAndKeyAndTenantId(String deploymentId, String processDefinitionKey, String tenantId) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findProcessDefinitionByParentDeploymentAndKey(String parentDeploymentId, String processDefinitionKey) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findProcessDefinitionByParentDeploymentAndKeyAndTenantId(String parentDeploymentId, String processDefinitionKey, String tenantId) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findProcessDefinitionByKeyAndVersion(String processDefinitionKey, Integer processDefinitionVersion) {
        return null;
    }

    @Override
    public ProcessDefinitionEntity findProcessDefinitionByKeyAndVersionAndTenantId(String processDefinitionKey, Integer processDefinitionVersion, String tenantId) {
        return null;
    }

    @Override
    public List<ProcessDefinition> findProcessDefinitionsByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findProcessDefinitionCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public void updateProcessDefinitionTenantIdForDeployment(String deploymentId, String newTenantId) {

    }

    @Override
    public void updateProcessDefinitionVersionForProcessDefinitionId(String processDefinitionId, int version) {

    }

    @Override
    public ProcessDefinitionEntity create() {
        return null;
    }
}
