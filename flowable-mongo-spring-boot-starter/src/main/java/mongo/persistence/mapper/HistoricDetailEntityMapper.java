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
package mongo.persistence.mapper;

import org.bson.Document;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.impl.persistence.entity.*;
import org.flowable.engine.impl.util.CommandContextUtil;

/**
 * @author Joram Barrez
 */
public class HistoricDetailEntityMapper
        extends AbstractEntityToDocumentMapper<HistoricDetailEntityImpl> {

    @Override
    public HistoricDetailEntityImpl fromDocument(Document document) {
        HistoricDetailEntityImpl historicDetailEntity = null;

        String type = document.getString("type");
        if ("variableUpdate".equals(type)) {
            HistoricDetailVariableInstanceUpdateEntityImpl historicDetailVariableInstanceUpdateEntity =
                    new HistoricDetailVariableInstanceUpdateEntityImpl();
            historicDetailVariableInstanceUpdateEntity.setName(document.getString("name"));
            historicDetailVariableInstanceUpdateEntity.setVariableType(
                    CommandContextUtil.getProcessEngineConfiguration()
                            .getVariableTypes()
                            .getVariableType(document.getString("typeName")));
            historicDetailVariableInstanceUpdateEntity.setDoubleValue(document.getDouble("doubleValue"));
            historicDetailVariableInstanceUpdateEntity.setTextValue(document.getString("textValue"));
            historicDetailVariableInstanceUpdateEntity.setTextValue2(document.getString("textValue2"));
            historicDetailVariableInstanceUpdateEntity.setLongValue(document.getLong("longValue"));

            // TODO: bytes

            historicDetailEntity = historicDetailVariableInstanceUpdateEntity;

        } else if ("formProperty".equals(type)) {
            HistoricFormPropertyEntityImpl historicFormPropertyEntity =
                    new HistoricFormPropertyEntityImpl();
            historicFormPropertyEntity.setPropertyId(document.getString("propertyId"));
            historicFormPropertyEntity.setPropertyValue(document.getString("propertyValue"));
            historicDetailEntity = historicFormPropertyEntity;

        } else {
            throw new FlowableException("Programmatic error: invalid type " + type);
        }

        historicDetailEntity.setId(document.getString("_id"));
        historicDetailEntity.setProcessInstanceId(document.getString("processInstanceId"));
        historicDetailEntity.setActivityInstanceId(document.getString("activityInstanceId"));
        historicDetailEntity.setExecutionId(document.getString("executionId"));
        historicDetailEntity.setTaskId(document.getString("taskId"));
        historicDetailEntity.setTime(document.getDate("time"));

        return historicDetailEntity;
    }

    @Override
    public Document toDocument(HistoricDetailEntityImpl entity) {
        // Note: no revision entity
        Document document = new Document();
        appendIfNotNull(document, "_id", entity.getId());
        appendIfNotNull(document, "processInstanceId", entity.getProcessInstanceId());
        appendIfNotNull(document, "activityInstanceId", entity.getActivityInstanceId());
        appendIfNotNull(document, "executionId", entity.getExecutionId());
        appendIfNotNull(document, "taskId", entity.getTaskId());
        appendIfNotNull(document, "time", entity.getTime());

        if (entity instanceof HistoricDetailVariableInstanceUpdateEntity variableInstanceUpdateEntity) {
            appendIfNotNull(document, "type", "variableUpdate");
            appendIfNotNull(document, "name", variableInstanceUpdateEntity.getName());
            appendIfNotNull(
                    document, "variableType", variableInstanceUpdateEntity.getVariableType().getTypeName());
            //            appendIfNotNull(document, "byteArrayRef",
            // variableInstanceUpdateEntity.getByteArrayRef() != null ?
            // variableInstanceUpdateEntity.getByteArrayRef().getBytes() : null);
            appendIfNotNull(document, "doubleValue", variableInstanceUpdateEntity.getDoubleValue());
            appendIfNotNull(document, "textValue", variableInstanceUpdateEntity.getTextValue());
            appendIfNotNull(document, "textValue2", variableInstanceUpdateEntity.getTextValue2());
            appendIfNotNull(document, "longValue", variableInstanceUpdateEntity.getLongValue());

        } else if (entity instanceof HistoricFormPropertyEntity historicFormPropertyEntity) {
            appendIfNotNull(document, "type", "formProperty");
            appendIfNotNull(document, "propertyId", historicFormPropertyEntity.getPropertyId());
            appendIfNotNull(document, "propertyValue", historicFormPropertyEntity.getPropertyValue());
        }

        return document;
    }
}
