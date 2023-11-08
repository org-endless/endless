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
import org.bson.conversions.Bson;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.eventsubscription.api.EventSubscription;
import org.flowable.eventsubscription.service.impl.EventSubscriptionQueryImpl;
import org.flowable.eventsubscription.service.impl.persistence.entity.*;
import org.flowable.eventsubscription.service.impl.persistence.entity.data.EventSubscriptionDataManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Joram Barrez
 */
public class MongoDbEventSubscriptionDataManager
        extends AbstractMongoDbDataManager<EventSubscriptionEntity>
        implements EventSubscriptionDataManager {


    @Override
    public String getCollection() {
        return COLLECTION_EVENT_SUBSCRIPTION;
    }

    @Override
    public EventSubscriptionEntity create() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MessageEventSubscriptionEntity createMessageEventSubscription() {
        return new MessageEventSubscriptionEntityImpl();
    }

    @Override
    public SignalEventSubscriptionEntity createSignalEventSubscription() {
        return new SignalEventSubscriptionEntityImpl();
    }

    @Override
    public CompensateEventSubscriptionEntity createCompensateEventSubscription() {
        return new CompensateEventSubscriptionEntityImpl();
    }

    @Override
    public GenericEventSubscriptionEntity createGenericEventSubscriptionEntity() {
        return new GenericEventSubscriptionEntityImpl();
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        return null;
    }

    @Override
    public long findEventSubscriptionCountByQueryCriteria(
            EventSubscriptionQueryImpl eventSubscriptionQuery) {
        List<Bson> andFilters = new ArrayList<>();
        if (eventSubscriptionQuery.getProcessInstanceId() != null) {
            andFilters.add(
                    Filters.eq("processInstanceId", eventSubscriptionQuery.getProcessInstanceId()));
        }

        Bson filter = null;
        if (andFilters.size() > 0) {
            filter = Filters.and(andFilters.toArray(new Bson[andFilters.size()]));
        }

        return getMongoDbSession().count(COLLECTION_EVENT_SUBSCRIPTION, filter);
    }

    @Override
    public List<EventSubscription> findEventSubscriptionsByQueryCriteria(
            EventSubscriptionQueryImpl eventSubscriptionQuery) {
        List<Bson> andFilters = new ArrayList<>();
        if (eventSubscriptionQuery.getProcessInstanceId() != null) {
            andFilters.add(
                    Filters.eq("processInstanceId", eventSubscriptionQuery.getProcessInstanceId()));
        }

        Bson filter = null;
        if (andFilters.size() > 0) {
            filter = Filters.and(andFilters.toArray(new Bson[andFilters.size()]));
        }

        return getMongoDbSession().find(COLLECTION_EVENT_SUBSCRIPTION, filter);
    }

    @Override
    public List<MessageEventSubscriptionEntity>
    findMessageEventSubscriptionsByProcessInstanceAndEventName(
            String processInstanceId, String eventName) {

        throw new UnsupportedOperationException();
    }

    @Override
    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByEventName(
            String eventName, String tenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SignalEventSubscriptionEntity>
    findSignalEventSubscriptionsByProcessInstanceAndEventName(
            String processInstanceId, String eventName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByNameAndExecution(
            String name, String executionId) {
        Bson filter =
                Filters.and(
                        Filters.eq("eventType", "signal"),
                        Filters.eq("eventName", name),
                        Filters.eq("executionId", executionId));
        return getMongoDbSession().find(COLLECTION_EVENT_SUBSCRIPTION, filter);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByExecutionAndType(
            String executionId, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByProcessInstanceAndType(String processInstanceId, String type) {
        return null;
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByProcessInstanceAndActivityId(
            String processInstanceId, String activityId, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByExecution(String executionId) {
        return getMongoDbSession()
                .find(COLLECTION_EVENT_SUBSCRIPTION, Filters.eq("executionId", executionId));
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByTypeAndProcessDefinitionId(
            String type, String processDefinitionId, String tenantId) {
        // TODO
        return Collections.emptyList();
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByScopeIdAndType(String scopeId, String type) {
        return null;
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByName(
            String type, String eventName, String tenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByNameAndExecution(
            String type, String eventName, String executionId) {
        Bson filter =
                Filters.and(
                        Filters.eq("eventType", type),
                        Filters.eq("eventName", eventName),
                        Filters.eq("executionId", executionId));
        return getMongoDbSession().find(COLLECTION_EVENT_SUBSCRIPTION, filter);
    }

    @Override
    public MessageEventSubscriptionEntity findMessageStartEventSubscriptionByName(
            String messageName, String tenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByScopeAndEventName(
            String scopeId, String scopeType, String eventName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsBySubScopeId(String subScopeId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteEventSubscriptionsForScopeIdAndType(String scopeId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteEventSubscriptionsForScopeDefinitionIdAndType(
            String scopeDefinitionId, String scopeType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteEventSubscriptionsForScopeDefinitionIdAndTypeAndNullScopeId(String scopeDefinitionId, String scopeType) {

    }

    @Override
    public void updateEventSubscriptionTenantId(String oldTenantId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateEventSubscriptionLockTime(String eventSubscriptionId, Date lockDate, String lockOwner, Date currentTime) {
        return false;
    }

    @Override
    public void clearEventSubscriptionLockTime(String eventSubscriptionId) {

    }

    @Override
    public void deleteEventSubscriptionsForProcessDefinition(String processDefinitionId) {
        getMongoDbSession()
                .bulkDelete(
                        COLLECTION_EVENT_SUBSCRIPTION,
                        Filters.and(
                                Filters.eq("processDefinitionId", processDefinitionId),
                                Filters.not(Filters.exists("executionId")),
                                Filters.not(Filters.exists("processInstanceId"))));
    }

    @Override
    public void deleteEventSubscriptionsByExecutionId(String executionId) {
        List<EventSubscriptionEntity> eventSubscriptions =
                findEventSubscriptionsByExecution(executionId);
        for (EventSubscriptionEntity eventSubscriptionEntity : eventSubscriptions) {
            delete(eventSubscriptionEntity);
        }
    }
}
