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
import mongo.config.MongoDbProcessEngineConfiguration;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.engine.history.HistoricDetail;
import org.flowable.engine.impl.HistoricDetailQueryImpl;
import org.flowable.engine.impl.persistence.entity.*;
import org.flowable.engine.impl.persistence.entity.data.HistoricDetailDataManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class MongoDbHistoricDetailDataManager
        extends AbstractMongoDbDataManager<HistoricDetailEntity> implements HistoricDetailDataManager {

    public static final String COLLECTION_HISTORIC_DETAILS = "historicDetails";

    public MongoDbHistoricDetailDataManager(
            MongoDbProcessEngineConfiguration processEngineConfiguration) {
        super(mongoOperations, processEngineConfiguration);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_DETAILS;
    }

    @Override
    public HistoricDetailEntity create() {
        // Superclass is abstract
        throw new UnsupportedOperationException();
    }

    @Override
    public HistoricDetailAssignmentEntity createHistoricDetailAssignment() {
        return new HistoricDetailAssignmentEntityImpl();
    }

    @Override
    public HistoricDetailVariableInstanceUpdateEntity createHistoricDetailVariableInstanceUpdate() {
        return new HistoricDetailVariableInstanceUpdateEntityImpl();
    }

    @Override
    public HistoricFormPropertyEntity createHistoricFormProperty() {
        return new HistoricFormPropertyEntityImpl();
    }

    @Override
    public List<HistoricDetailEntity> findHistoricDetailsByProcessInstanceId(
            String processInstanceId) {
        return getMongoDbSession()
                .find(COLLECTION_HISTORIC_DETAILS, Filters.eq("processInstanceId", processInstanceId));
    }

    @Override
    public List<HistoricDetailEntity> findHistoricDetailsByTaskId(String taskId) {
        return getMongoDbSession().find(COLLECTION_HISTORIC_DETAILS, Filters.eq("taskId", taskId));
    }

    @Override
    public long findHistoricDetailCountByQueryCriteria(
            HistoricDetailQueryImpl historicVariableUpdateQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricDetail> findHistoricDetailsByQueryCriteria(
            HistoricDetailQueryImpl historicVariableUpdateQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricDetail> findHistoricDetailsByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findHistoricDetailCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bulkDeleteHistoricDetailsByProcessInstanceIds(Collection<String> historicProcessInstanceIds) {

    }

    @Override
    public void bulkDeleteHistoricDetailsByTaskIds(Collection<String> taskIds) {

    }

    @Override
    public void deleteHistoricDetailForNonExistingProcessInstances() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        return null;
    }
}
