/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package persistence.manager;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.identitylink.service.impl.persistence.entity.IdentityLinkEntity;
import org.flowable.identitylink.service.impl.persistence.entity.IdentityLinkEntityImpl;
import org.flowable.identitylink.service.impl.persistence.entity.data.IdentityLinkDataManager;

import java.util.Collection;
import java.util.List;

/**
 * @author Joram Barrez
 */
public class MongoDbIdentityLinkDataManager extends AbstractMongoDbDataManager<IdentityLinkEntity>
        implements IdentityLinkDataManager {

    public static String COLLECTION_IDENTITY_LINKS = "identityLinks";

    public MongoDbIdentityLinkDataManager() {
        super(mongoOperations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_IDENTITY_LINKS;
    }

    @Override
    public IdentityLinkEntity create() {
        return new IdentityLinkEntityImpl();
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        return null;
    }

    @Override
    public IdentityLinkEntity createIdentityLinkFromHistoricIdentityLink(HistoricIdentityLink historicIdentityLink) {
        return null;
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByTaskId(String taskId) {
        return getMongoDbSession().find(COLLECTION_IDENTITY_LINKS, Filters.eq("taskId"));
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByProcessInstanceId(String processInstanceId) {
        return getMongoDbSession()
                .find(COLLECTION_IDENTITY_LINKS, Filters.eq("processInstanceId", processInstanceId));
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByProcessDefinitionId(
            String processDefinitionId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByScopeIdAndType(
            String scopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksBySubScopeIdAndType(
            String subScopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinksByScopeDefinitionIdAndType(
            String scopeDefinitionId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByTaskUserGroupAndType(
            String taskId, String userId, String groupId, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByProcessInstanceUserGroupAndType(
            String processInstanceId, String userId, String groupId, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByProcessDefinitionUserAndGroup(
            String processDefinitionId, String userId, String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByScopeIdScopeTypeUserGroupAndType(
            String scopeId, String scopeType, String userId, String groupId, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityLinkEntity> findIdentityLinkByScopeDefinitionScopeTypeUserAndGroup(
            String scopeDefinitionId, String scopeType, String userId, String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteIdentityLinksByTaskId(String taskId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteIdentityLinksByProcDef(String processDefinitionId) {
        getMongoDbSession()
                .bulkDelete(
                        COLLECTION_IDENTITY_LINKS, Filters.eq("processDefinitionId", processDefinitionId));
    }

    @Override
    public void deleteIdentityLinksByProcessInstanceId(String processInstanceId) {
        List<IdentityLinkEntity> identityLinks =
                findIdentityLinksByProcessInstanceId(processInstanceId);
        for (IdentityLinkEntity identityLinkEntity : identityLinks) {
            delete(identityLinkEntity);
        }
    }

    @Override
    public void deleteIdentityLinksByScopeIdAndScopeType(String scopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteIdentityLinksByScopeDefinitionIdAndScopeType(
            String scopeDefinitionId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bulkDeleteIdentityLinksForScopeIdsAndScopeType(Collection<String> scopeIds, String scopeType) {

    }
}
