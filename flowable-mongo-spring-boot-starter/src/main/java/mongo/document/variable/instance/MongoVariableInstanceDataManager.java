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
package mongo.document.variable.instance;

import mongo.persistence.manager.MongoDbDataManager;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.variable.api.persistence.entity.VariableInstance;
import org.flowable.variable.service.impl.InternalVariableInstanceQueryImpl;
import org.flowable.variable.service.impl.VariableInstanceQueryImpl;
import org.flowable.variable.service.impl.persistence.entity.VariableInstanceEntity;
import org.flowable.variable.service.impl.persistence.entity.data.VariableInstanceDataManager;
import org.flowable.variable.service.impl.persistence.entity.data.impl.cachematcher.VariableInstanceByExecutionIdMatcher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
@Import(MongoVariableInstanceRepository.class)
public class MongoVariableInstanceDataManager
        implements MongoDbDataManager<VariableInstanceEntity>, VariableInstanceDataManager {

    protected VariableInstanceByExecutionIdMatcher variableInstanceByExecutionIdMatcher =
            new VariableInstanceByExecutionIdMatcher();

    private final MongoOperations operations;
    private final MongoVariableInstanceRepository repository;

    public MongoVariableInstanceDataManager(
            @Qualifier("mongoTemplate") MongoOperations operations,
            MongoVariableInstanceRepository repository) {
        this.operations = operations;
        this.repository = repository;
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
    public List<VariableInstanceEntity> findVariablesInstancesByQuery(
            InternalVariableInstanceQueryImpl internalVariableInstanceQuery) {

        var query = new Query();

        if (!internalVariableInstanceQuery.isWithoutTaskId()) {
            var taskId = internalVariableInstanceQuery.getTaskId();
            if (StringUtils.hasLength(taskId)) {
                query.addCriteria(Criteria.where("TASK_ID_").is(taskId));
            }

            var taskIds = internalVariableInstanceQuery.getTaskIds();
            if (CollectionUtils.isEmpty(taskIds)) {
                query.addCriteria(Criteria.where("TASK_ID_").in(taskIds));
            }
        }

        var executionId = internalVariableInstanceQuery.getExecutionId();
        if (StringUtils.hasLength(executionId)) {
            query.addCriteria(Criteria.where("EXECUTION_ID_").is(executionId));
        }

        var executionIds = internalVariableInstanceQuery.getNames();
        if (!CollectionUtils.isEmpty(executionIds)) {
            query.addCriteria(Criteria.where("EXECUTION_ID_").in(executionIds));
        }

        var scopeId = internalVariableInstanceQuery.getScopeId();
        if (StringUtils.hasLength(scopeId)) {
            query.addCriteria(Criteria.where("SCOPE_ID_").is(scopeId));
        }

        if (!internalVariableInstanceQuery.isWithoutSubScopeId()) {
            var subScopeId = internalVariableInstanceQuery.getSubScopeId();
            if (StringUtils.hasLength(subScopeId)) {
                query.addCriteria(Criteria.where("SUB_SCOPE_ID_").is(subScopeId));
            }

            var subScopeIds = internalVariableInstanceQuery.getSubScopeIds();
            if (CollectionUtils.isEmpty(subScopeIds)) {
                query.addCriteria(Criteria.where("SUB_SCOPE_ID_").in(subScopeIds));
            }
        }

        var scopeType = internalVariableInstanceQuery.getScopeType();
        if (StringUtils.hasLength(scopeType)) {
            query.addCriteria(Criteria.where("SCOPE_TYPE_").is(scopeType));
        }

        var scopeTypes = internalVariableInstanceQuery.getScopeTypes();
        if (CollectionUtils.isEmpty(scopeTypes)) {
            query.addCriteria(Criteria.where("SCOPE_TYPE_").in(scopeTypes));
        }

        String name = internalVariableInstanceQuery.getName();
        if (StringUtils.hasLength(name)) {
            query.addCriteria(Criteria.where("NAME_").is(name));
        }

        var names = internalVariableInstanceQuery.getNames();
        if (!CollectionUtils.isEmpty(names)) {
            query.addCriteria(Criteria.where("NAME_").in(names));
        }

        return new ArrayList<>(operations.find(query, MongoVariableInstanceDocument.class));
    }

    @Override
    public VariableInstanceEntity findVariablesInstanceByQuery(
            InternalVariableInstanceQueryImpl internalVariableInstanceQuery) {
        var variableInstanceEntities =
                findVariablesInstancesByQuery(internalVariableInstanceQuery);
        if (variableInstanceEntities.size() > 1) {
            throw new FlowableIllegalArgumentException(
                    "Query returned more than one result: " + variableInstanceEntities);
        }
        if (variableInstanceEntities.size() == 1) {
            return variableInstanceEntities.get(0);
        }
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
        repository.deleteAllByTaskId(taskId);
    }

    @Override
    public void deleteVariablesByExecutionId(String executionId) {
        repository.deleteAllByExecutionId(executionId);
    }

    @Override
    public void deleteByScopeIdAndScopeType(String scopeId, String scopeType) {
        repository.deleteAllByScopeIdAndScopeType(scopeId, scopeType);
    }

    @Override
    public void deleteByScopeIdAndScopeTypes(String scopeId, Collection<String> scopeTypes) {
        repository.deleteAllByScopeIdAndScopeTypeIn(scopeId, scopeTypes);
    }

    @Override
    public void deleteBySubScopeIdAndScopeTypes(String subScopeId, Collection<String> scopeTypes) {
        repository.deleteAllBySubScopeIdAndScopeTypeIn(subScopeId, scopeTypes);
    }

    @Override
    public VariableInstanceEntity create() {
        return new MongoVariableInstanceDocument();
    }

    @Override
    public VariableInstanceEntity findById(String entityId) {
        return repository.findById(entityId).orElse(null);
    }

    @Override
    public void insert(VariableInstanceEntity entity) {
        operations.insert((MongoVariableInstanceDocument) entity);
    }

    @Override
    public VariableInstanceEntity update(VariableInstanceEntity entity) {
        return operations.findAndModify(getQueryObjectById(entity), getUpdateObject(entity), FindAndModifyOptions.options().returnNew(true), MongoVariableInstanceDocument.class);
    }

    @Override
    public void delete(String entityId) {
        operations.remove(getQueryObjectById(entityId));
    }

    @Override
    public void delete(VariableInstanceEntity entity) {
        operations.remove(getQueryObjectById(entity));
    }
}
