package document.identity.link;

import document.AbstractMongoDataManager;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.identitylink.service.impl.persistence.entity.IdentityLinkEntity;
import org.flowable.identitylink.service.impl.persistence.entity.data.IdentityLinkDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * MongoIdentityLinkDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:34
 * <p>update 2023/9/22 16:34
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoIdentityLinkDataManager extends AbstractMongoDataManager<IdentityLinkEntity>
        implements IdentityLinkDataManager {

    public static final String COLLECTION_IDENTITY_LINK = "ACT_HI_IDENTITYLINK";

    public MongoIdentityLinkDataManager(MongoOperations operations) {
        super(operations);
    }


    @Override
    public String getCollection() {
        return COLLECTION_IDENTITY_LINK;
    }

    @Override
    public Class<IdentityLinkEntity> getEntityClass() {
        return IdentityLinkEntity.class;
    }

    @Override
    public Update getUpdateObject(IdentityLinkEntity entity) {
        return null;
    }

    @Override
    public IdentityLinkEntity createIdentityLinkFromHistoricIdentityLink(HistoricIdentityLink historicIdentityLink) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByTaskId(String taskId) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByProcessDefinitionId(String processDefinitionId) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByScopeIdAndType(String scopeId, String scopeType) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksBySubScopeIdAndType(String subScopeId, String scopeType) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByScopeDefinitionIdAndType(String scopeDefinitionId, String scopeType) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByTaskUserGroupAndType(String taskId, String userId, String groupId, String type) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByProcessInstanceUserGroupAndType(String processInstanceId, String userId, String groupId, String type) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByProcessDefinitionUserAndGroup(String processDefinitionId, String userId, String groupId) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByScopeIdScopeTypeUserGroupAndType(String scopeId, String scopeType, String userId, String groupId, String type) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByScopeDefinitionScopeTypeUserAndGroup(String scopeDefinitionId, String scopeType, String userId, String groupId) {
        return null;
    }

    @Override
    public void deleteIdentityLinksByTaskId(String taskId) {

    }

    @Override
    public void deleteIdentityLinksByProcDef(String processDefId) {

    }

    @Override
    public void deleteIdentityLinksByProcessInstanceId(String processInstanceId) {

    }

    @Override
    public void deleteIdentityLinksByScopeIdAndScopeType(String scopeId, String scopeType) {

    }

    @Override
    public void deleteIdentityLinksByScopeDefinitionIdAndScopeType(String scopeDefinitionId, String scopeType) {

    }

    @Override
    public void bulkDeleteIdentityLinksForScopeIdsAndScopeType(Collection<String> scopeIds, String scopeType) {

    }

    @Override
    public IdentityLinkEntity create() {
        return null;
    }
}
