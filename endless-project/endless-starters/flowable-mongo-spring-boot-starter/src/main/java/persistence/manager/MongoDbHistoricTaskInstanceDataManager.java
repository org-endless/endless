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
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.impl.HistoricTaskInstanceQueryImpl;
import org.flowable.task.service.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.flowable.task.service.impl.persistence.entity.HistoricTaskInstanceEntityImpl;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.flowable.task.service.impl.persistence.entity.data.HistoricTaskInstanceDataManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Tijs Rademakers
 * @author Joram Barrez
 */
public class MongoDbHistoricTaskInstanceDataManager
        extends AbstractMongoDbDataManager<HistoricTaskInstanceEntity>
        implements HistoricTaskInstanceDataManager {

    public static final String COLLECTION_HISTORIC_TASK_INSTANCES = "historicTaskInstances";

    public MongoDbHistoricTaskInstanceDataManager() {
        super(mongoOperations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_HISTORIC_TASK_INSTANCES;
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        HistoricTaskInstanceEntity historicTaskInstanceEntity = (HistoricTaskInstanceEntity) entity;
        BasicDBObject updateObject = null;
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "processDefinitionId",
                        historicTaskInstanceEntity.getProcessDefinitionId(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "executionId",
                        historicTaskInstanceEntity.getExecutionId(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity, "name", historicTaskInstanceEntity.getName(), updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "parentTaskId",
                        historicTaskInstanceEntity.getParentTaskId(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "description",
                        historicTaskInstanceEntity.getDescription(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "owner",
                        historicTaskInstanceEntity.getOwner(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "assignee",
                        historicTaskInstanceEntity.getAssignee(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "claimTime",
                        historicTaskInstanceEntity.getClaimTime(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "endTime",
                        historicTaskInstanceEntity.getEndTime(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "duration",
                        historicTaskInstanceEntity.getDurationInMillis(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "deleteReason",
                        historicTaskInstanceEntity.getDeleteReason(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "taskDefinitionKey",
                        historicTaskInstanceEntity.getTaskDefinitionKey(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "formKey",
                        historicTaskInstanceEntity.getFormKey(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "priority",
                        historicTaskInstanceEntity.getPriority(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "dueDate",
                        historicTaskInstanceEntity.getDueDate(),
                        updateObject);
        updateObject =
                setUpdateProperty(
                        historicTaskInstanceEntity,
                        "category",
                        historicTaskInstanceEntity.getCategory(),
                        updateObject);
        return updateObject;
    }

    @Override
    public HistoricTaskInstanceEntity create() {
        return new HistoricTaskInstanceEntityImpl();
    }

    @Override
    public HistoricTaskInstanceEntity create(TaskEntity task) {
        return new HistoricTaskInstanceEntityImpl(task);
    }

    @Override
    public List<HistoricTaskInstanceEntity> findHistoricTasksByParentTaskId(String parentTaskId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> findHistoricTaskIdsByParentTaskIds(Collection<String> parentTaskIds) {
        return null;
    }

    @Override
    public List<HistoricTaskInstanceEntity> findHistoricTasksByProcessInstanceId(
            String processInstanceId) {
        // TODO: needs cache matcher (also in mybatis counterpart) ?
        return getMongoDbSession()
                .find(
                        COLLECTION_HISTORIC_TASK_INSTANCES, Filters.eq("processInstanceId", processInstanceId));
    }

    @Override
    public List<String> findHistoricTaskIdsForProcessInstanceIds(Collection<String> processInstanceIds) {
        return null;
    }

    @Override
    public List<String> findHistoricTaskIdsForScopeIdsAndScopeType(Collection<String> scopeIds, String scopeType) {
        return null;
    }

    @Override
    public long findHistoricTaskInstanceCountByQueryCriteria(
            HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricTaskInstance> findHistoricTaskInstancesByQueryCriteria(
            HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricTaskInstance> findHistoricTaskInstancesAndRelatedEntitiesByQueryCriteria(
            HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricTaskInstance> findHistoricTaskInstancesByNativeQuery(
            Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findHistoricTaskInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteHistoricTaskInstances(HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bulkDeleteHistoricTaskInstancesForIds(Collection<String> taskIds) {

    }

    @Override
    public void deleteHistoricTaskInstancesForNonExistingProcessInstances() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteHistoricTaskInstancesForNonExistingCaseInstances() {
        throw new UnsupportedOperationException();
    }
}
