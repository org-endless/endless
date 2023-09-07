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
package mongo.persistence.manager;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.flowable.variable.service.impl.HistoricVariableInstanceQueryImpl;
import org.flowable.variable.service.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.flowable.variable.service.impl.persistence.entity.HistoricVariableInstanceEntityImpl;
import org.flowable.variable.service.impl.persistence.entity.data.HistoricVariableInstanceDataManager;
import org.flowable.variable.service.impl.persistence.entity.data.impl.cachematcher.HistoricVariableInstanceByProcInstMatcher;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Tijs Rademakers
 */
public class MongoDbHistoricVariableInstanceDataManager
        extends AbstractMongoDbDataManager<HistoricVariableInstanceEntity>
        implements HistoricVariableInstanceDataManager {

    public static final String COLLECTION_HISTORIC_VARIABLE_INSTANCES = "historicVariableInstances";

    protected HistoricVariableInstanceByProcInstMatcher historicVariableInstanceByProcInstMatcher =
            new HistoricVariableInstanceByProcInstMatcher();

    public MongoDbHistoricVariableInstanceDataManager() {
        super(mongoOperations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_VARIABLE_INSTANCES;
    }

    @Override
    public HistoricVariableInstanceEntity create() {
        return new HistoricVariableInstanceEntityImpl();
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        return null;
    }

    @Override
    public List<HistoricVariableInstanceEntity> findHistoricVariableInstancesByProcessInstanceId(
            String processInstanceId) {
        return getMongoDbSession()
                .find(
                        COLLECTION_HISTORIC_VARIABLE_INSTANCES,
                        Filters.eq("processInstanceId", processInstanceId),
                        processInstanceId,
                        HistoricVariableInstanceEntityImpl.class,
                        historicVariableInstanceByProcInstMatcher);
    }

    @Override
    public List<HistoricVariableInstanceEntity> findHistoricVariableInstancesByTaskId(String taskId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findHistoricVariableInstanceCountByQueryCriteria(
            HistoricVariableInstanceQueryImpl historicProcessVariableQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricVariableInstance> findHistoricVariableInstancesByQueryCriteria(
            HistoricVariableInstanceQueryImpl historicProcessVariableQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HistoricVariableInstanceEntity findHistoricVariableInstanceByVariableInstanceId(
            String variableInstanceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricVariableInstanceEntity> findHistoricalVariableInstancesByScopeIdAndScopeType(
            String scopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricVariableInstanceEntity>
    findHistoricalVariableInstancesBySubScopeIdAndScopeType(String subScopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricVariableInstance> findHistoricVariableInstancesByNativeQuery(
            Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findHistoricVariableInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bulkDeleteHistoricVariableInstancesByProcessInstanceIds(Collection<String> processInstanceIds) {

    }

    @Override
    public void bulkDeleteHistoricVariableInstancesByTaskIds(Collection<String> taskIds) {

    }

    @Override
    public void bulkDeleteHistoricVariableInstancesByScopeIdsAndScopeType(Collection<String> scopeIds, String scopeType) {

    }

    @Override
    public void deleteHistoricVariableInstancesForNonExistingProcessInstances() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteHistoricVariableInstancesForNonExistingCaseInstances() {
        throw new UnsupportedOperationException();
    }
}
