package document.historic.identity.link;

import document.AbstractMongoDataManager;
import org.flowable.identitylink.service.impl.persistence.entity.HistoricIdentityLinkEntity;
import org.flowable.identitylink.service.impl.persistence.entity.data.HistoricIdentityLinkDataManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * MongoHistoricIdentityLinkDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:32
 * <p>update 2023/9/22 16:32
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoHistoricIdentityLinkDataManager extends AbstractMongoDataManager<HistoricIdentityLinkEntity>
        implements HistoricIdentityLinkDataManager {

    public static final String COLLECTION_HISTORIC_IDENTITY_LINK = "ACT_HI_IDENTITYLINK";

    public MongoHistoricIdentityLinkDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_IDENTITY_LINK;
    }

    @Override
    public Class<HistoricIdentityLinkEntity> getEntityClass() {
        return HistoricIdentityLinkEntity.class;
    }

    @Override
    public Update getUpdateObject(HistoricIdentityLinkEntity entity) {
        return null;
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksByTaskId(String taskId) {
        return null;
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksByScopeIdAndScopeType(String scopeId, String scopeType) {
        return null;
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksBySubScopeIdAndScopeType(String subScopeId, String scopeType) {
        return null;
    }

    @Override
    public void deleteHistoricIdentityLinksByScopeIdAndType(String scopeId, String scopeType) {

    }

    @Override
    public void deleteHistoricIdentityLinksByScopeDefinitionIdAndType(String scopeDefinitionId, String scopeType) {

    }

    @Override
    public void bulkDeleteHistoricIdentityLinksForProcessInstanceIds(Collection<String> processInstanceIds) {

    }

    @Override
    public void bulkDeleteHistoricIdentityLinksForTaskIds(Collection<String> taskIds) {

    }

    @Override
    public void bulkDeleteHistoricIdentityLinksForScopeIdsAndScopeType(Collection<String> scopeIds, String scopeType) {

    }

    @Override
    public void deleteHistoricProcessIdentityLinksForNonExistingInstances() {

    }

    @Override
    public void deleteHistoricCaseIdentityLinksForNonExistingInstances() {

    }

    @Override
    public void deleteHistoricTaskIdentityLinksForNonExistingInstances() {

    }

    @Override
    public HistoricIdentityLinkEntity create() {
        return null;
    }
}
