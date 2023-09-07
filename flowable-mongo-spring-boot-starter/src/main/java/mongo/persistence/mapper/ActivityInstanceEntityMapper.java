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
import org.flowable.engine.impl.persistence.entity.ActivityInstanceEntityImpl;

/**
 * @author Joram Barrez
 */
public class ActivityInstanceEntityMapper
        extends AbstractEntityToDocumentMapper<ActivityInstanceEntityImpl> {

    @Override
    public ActivityInstanceEntityImpl fromDocument(Document document) {
        ActivityInstanceEntityImpl activityEntity = new ActivityInstanceEntityImpl();
        activityEntity.setId(document.getString("_id"));
        activityEntity.setRevision(document.getInteger("revision"));
        activityEntity.setActivityId(document.getString("activityId"));
        activityEntity.setActivityName(document.getString("activityName"));
        activityEntity.setActivityType(document.getString("activityType"));
        activityEntity.setProcessInstanceId(document.getString("processInstanceId"));
        activityEntity.setProcessDefinitionId(document.getString("processDefinitionId"));
        activityEntity.setAssignee(document.getString("assignee"));
        activityEntity.setCalledProcessInstanceId(document.getString("calledProcessInstanceId"));
        activityEntity.setDeleteReason(document.getString("deleteReason"));
        activityEntity.setDurationInMillis(document.getLong("durationInMillis"));
        activityEntity.setEndTime(document.getDate("endTime"));
        activityEntity.setExecutionId(document.getString("executionId"));
        activityEntity.setRevision(document.getInteger("revision"));
        activityEntity.setStartTime(document.getDate("startTime"));
        activityEntity.setTaskId(document.getString("taskId"));
        activityEntity.setTenantId(document.getString("tenantId"));

        return activityEntity;
    }

    @Override
    public Document toDocument(ActivityInstanceEntityImpl activityEntity) {
        Document historicActivityInstanceDocument = new Document();
        appendIfNotNull(historicActivityInstanceDocument, "_id", activityEntity.getId());
        appendIfNotNull(historicActivityInstanceDocument, "revision", activityEntity.getRevision());
        appendIfNotNull(historicActivityInstanceDocument, "activityId", activityEntity.getActivityId());
        appendIfNotNull(
                historicActivityInstanceDocument, "activityName", activityEntity.getActivityName());
        appendIfNotNull(
                historicActivityInstanceDocument, "activityType", activityEntity.getActivityType());
        appendIfNotNull(
                historicActivityInstanceDocument,
                "processInstanceId",
                activityEntity.getProcessInstanceId());
        appendIfNotNull(
                historicActivityInstanceDocument,
                "processDefinitionId",
                activityEntity.getProcessDefinitionId());
        appendIfNotNull(historicActivityInstanceDocument, "assignee", activityEntity.getAssignee());
        appendIfNotNull(
                historicActivityInstanceDocument,
                "calledProcessInstanceId",
                activityEntity.getCalledProcessInstanceId());
        appendIfNotNull(
                historicActivityInstanceDocument, "deleteReason", activityEntity.getDeleteReason());
        appendIfNotNull(
                historicActivityInstanceDocument, "durationInMillis", activityEntity.getDurationInMillis());
        appendIfNotNull(historicActivityInstanceDocument, "endTime", activityEntity.getEndTime());
        appendIfNotNull(
                historicActivityInstanceDocument, "executionId", activityEntity.getExecutionId());
        appendIfNotNull(historicActivityInstanceDocument, "revision", activityEntity.getRevision());
        appendIfNotNull(historicActivityInstanceDocument, "startTime", activityEntity.getStartTime());
        appendIfNotNull(historicActivityInstanceDocument, "taskId", activityEntity.getTaskId());
        appendIfNotNull(historicActivityInstanceDocument, "tenantId", activityEntity.getTenantId());

        return historicActivityInstanceDocument;
    }
}
