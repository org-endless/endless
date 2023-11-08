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
import org.flowable.identitylink.service.impl.persistence.entity.HistoricIdentityLinkEntity;
import org.flowable.identitylink.service.impl.persistence.entity.HistoricIdentityLinkEntityImpl;
import org.flowable.identitylink.service.impl.persistence.entity.data.HistoricIdentityLinkDataManager;
import org.flowable.identitylink.service.impl.persistence.entity.data.impl.cachematcher.HistoricIdentityLinksByProcInstMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author Tijs Rademakers
 * @author Joram Barrez
 */
public class MongoDbHistoricIdentityLinkDataManager
        extends AbstractMongoDbDataManager<HistoricIdentityLinkEntity>
        implements HistoricIdentityLinkDataManager {

    public static final String COLLECTION_HISTORIC_IDENTITY_LINKS = "historicIdentityLinks";

    protected HistoricIdentityLinksByProcInstMatcher historicIdentityLinksByProcInstMatcher =
            new HistoricIdentityLinksByProcInstMatcher();

    public MongoDbHistoricIdentityLinkDataManager() {
        super(mongoOperations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_IDENTITY_LINKS;
    }

    @Override
    public HistoricIdentityLinkEntity create() {
        return new HistoricIdentityLinkEntityImpl();
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        return null;
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksByTaskId(String taskId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksByProcessInstanceId(
            String processInstanceId) {
        return getMongoDbSession()
                .find(
                        COLLECTION_HISTORIC_IDENTITY_LINKS,
                        Filters.eq("processInstanceId", processInstanceId),
                        processInstanceId,
                        HistoricIdentityLinkEntityImpl.class,
                        historicIdentityLinksByProcInstMatcher);
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksByScopeIdAndScopeType(
            String scopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksBySubScopeIdAndScopeType(
            String subScopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteHistoricIdentityLinksByScopeIdAndType(String scopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteHistoricIdentityLinksByScopeDefinitionIdAndType(
            String scopeDefinitionId, String scopeType) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteHistoricCaseIdentityLinksForNonExistingInstances() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteHistoricTaskIdentityLinksForNonExistingInstances() {
        throw new UnsupportedOperationException();
    }
}
