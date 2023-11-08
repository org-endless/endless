package document.event.subscription;

import document.AbstractMongoDataManager;
import lombok.extern.slf4j.Slf4j;
import org.flowable.eventsubscription.api.EventSubscription;
import org.flowable.eventsubscription.service.impl.EventSubscriptionQueryImpl;
import org.flowable.eventsubscription.service.impl.persistence.entity.*;
import org.flowable.eventsubscription.service.impl.persistence.entity.data.EventSubscriptionDataManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * MongoEventSubscriptionDataManager
 * <p>
 * <p>
 * <p>create 2023/9/18 12:39
 * <p>update 2023/9/18 12:39
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
@Import({MongoEventSubscriptionRepository.class})
public class MongoEventSubscriptionDataManager extends AbstractMongoDataManager<EventSubscriptionEntity>
        implements EventSubscriptionDataManager {

    public static final String COLLECTION_EVENT_SUBSCRIPTION = "ACT_RU_EVENT_SUBSCR";

    private final MongoEventSubscriptionRepository repository;

    public MongoEventSubscriptionDataManager(MongoOperations operations, MongoEventSubscriptionRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_EVENT_SUBSCRIPTION;
    }

    @Override
    public Class<EventSubscriptionEntity> getEntityClass() {
        return EventSubscriptionEntity.class;
    }

    @Override
    public Update getUpdateObject(EventSubscriptionEntity entity) {
        return Update.update("REV_", entity.getRevision())
                .set("ACTIVITY_ID_", entity.getActivityId())
                .set("EXECUTION_ID_", entity.getExecutionId())
                .set("PROC_INST_ID_", entity.getProcessDefinitionId())
                .set("PROC_DEF_ID_", entity.getProcessDefinitionId())
                .set("TENANT_ID_", entity.getTenantId())
                .set("SCOPE_ID_", entity.getScopeId())
                .set("SUB_SCOPE_ID_", entity.getSubScopeId())
                .set("SCOPE_DEFINITION_ID_", entity.getScopeDefinitionId())
                .set("SCOPE_TYPE_", entity.getScopeType())
                .set("EVENT_TYPE_", entity.getEventType())
                .set("EVENT_NAME_", entity.getEventName())
                .set("CONFIGURATION_", entity.getConfiguration())
                .set("CREATED_", entity.getCreated())
                .set("LOCK_TIME_", entity.getLockTime())
                .set("LOCK_OWNER_", entity.getLockOwner());
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
    public long findEventSubscriptionCountByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQueryImpl) {
        return repository.countByProcessInstanceId(eventSubscriptionQueryImpl.getProcessInstanceId());
    }

    @Override
    public List<EventSubscription> findEventSubscriptionsByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQueryImpl) {
        return repository.findByProcessInstanceId(eventSubscriptionQueryImpl.getProcessInstanceId());
    }

    @Override
    public List<MessageEventSubscriptionEntity> findMessageEventSubscriptionsByProcessInstanceAndEventName(String processInstanceId, String eventName) {
        return repository.findByProcessInstanceIdAndEventNameAndEventType(processInstanceId, eventName, "message")
                .stream().map(entity -> (MessageEventSubscriptionEntity) entity).toList();
    }

    @Override
    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByEventName(String eventName, String tenantId) {
        // TODO mongo join select
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByProcessInstanceAndEventName(String processInstanceId, String eventName) {
        return repository.findByProcessInstanceIdAndEventNameAndEventType(processInstanceId, eventName, "signal")
                .stream().map(entity -> (SignalEventSubscriptionEntity) entity).toList();
    }

    @Override
    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByScopeAndEventName(String scopeId, String scopeType, String eventName) {
        return repository.findByScopeIdAndScopeTypeAndEventNameAndEventType(scopeId, scopeType, eventName, "signal")
                .stream().map(entity -> (SignalEventSubscriptionEntity) entity).toList();
    }

    @Override
    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByNameAndExecution(String name, String executionId) {
        return repository.findByEventNameAndExecutionIdAndEventType(name, executionId, "signal")
                .stream().map(entity -> (SignalEventSubscriptionEntity) entity).toList();
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByExecutionAndType(String executionId, String type) {
        return repository.findByExecutionIdAndEventType(executionId, type);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByProcessInstanceAndType(String processInstanceId, String type) {
        return repository.findByProcessInstanceIdAndEventType(processInstanceId, type);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByProcessInstanceAndActivityId(String processInstanceId, String activityId, String type) {
        return repository.findByProcessInstanceIdAndActivityIdAndEventType(processInstanceId, activityId, type);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByExecution(String executionId) {
        return repository.findByExecutionId(executionId);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsBySubScopeId(String subScopeId) {
        return repository.findBySubScopeId(subScopeId);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByTypeAndProcessDefinitionId(String type, String processDefinitionId, String tenantId) {
        return repository.findByEventTypeAndProcessDefinitionIdAndTenantIdAndExecutionIdAndProcessInstanceId(type, processDefinitionId, tenantId, null, null);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByScopeIdAndType(String scopeId, String type) {
        return repository.findByScopeIdAndEventType(scopeId, type);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByName(String type, String eventName, String tenantId) {
        return repository.findByEventTypeAndEventNameAndTenantId(type, eventName, tenantId);
    }

    @Override
    public List<EventSubscriptionEntity> findEventSubscriptionsByNameAndExecution(String type, String eventName, String executionId) {
        return repository.findByEventTypeAndEventNameAndExecutionId(type, eventName, executionId);
    }

    @Override
    public MessageEventSubscriptionEntity findMessageStartEventSubscriptionByName(String messageName, String tenantId) {
        return (MessageEventSubscriptionEntity) repository.findByEventNameAndTenantIdAndExecutionIdAndEventType(messageName, tenantId, null, "message");
    }

    @Override
    public void updateEventSubscriptionTenantId(String oldTenantId, String newTenantId) {
        var query = Query.query(Criteria.where("TENANT_ID_").is(oldTenantId));
        var update = Update.update("TENANT_ID_", newTenantId);
        update(query, update);
    }

    @Override
    public boolean updateEventSubscriptionLockTime(String eventSubscriptionId, Date lockDate, String lockOwner, Date currentTime) {

        var query = Query.query(Criteria.where("ID_").is(eventSubscriptionId)
                .orOperator(Criteria.where("LOCK_TIME_").isNull(), Criteria.where("LOCK_TIME_").lt(currentTime)));

        var update = Update.update("LOCK_TIME_", lockDate)
                .set("LOCK_OWNER_", lockOwner);

        try {
            Objects.requireNonNull(update(query, update));
            return true;
        } catch (Exception e) {
            log.debug(e.getMessage());
            return false;
        }
    }

    @Override
    public void clearEventSubscriptionLockTime(String eventSubscriptionId) {
        var update = Update.update("LOCK_TIME_", null)
                .set("LOCK_OWNER_", null);
        update(getQueryObjectById(eventSubscriptionId), update);
    }

    @Override
    public void deleteEventSubscriptionsForProcessDefinition(String processDefinitionId) {
        var query = Query.query(Criteria.where("PROC_DEF_ID_").is(processDefinitionId)
                .and("EXECUTION_ID_").isNull()
                .and("PROC_INST_ID_").isNull());
        delete(query);
    }

    @Override
    public void deleteEventSubscriptionsByExecutionId(String executionId) {
        var query = Query.query(Criteria.where("EXECUTION_ID_").is(executionId));
        delete(query);
    }

    @Override
    public void deleteEventSubscriptionsForScopeIdAndType(String scopeId, String scopeType) {
        var query = Query.query(Criteria.where("SCOPE_ID_").is(scopeId)
                .and("SCOPE_TYPE_").is(scopeType));
        delete(query);
    }

    @Override
    public void deleteEventSubscriptionsForScopeDefinitionIdAndType(String scopeDefinitionId, String scopeType) {
        var query = Query.query(Criteria.where("SCOPE_DEFINITION_ID_").is(scopeDefinitionId)
                .and("SCOPE_TYPE_").is(scopeType));
        delete(query);
    }

    @Override
    public void deleteEventSubscriptionsForScopeDefinitionIdAndTypeAndNullScopeId(String scopeDefinitionId, String scopeType) {
        var query = Query.query(Criteria.where("SCOPE_DEFINITION_ID_").is(scopeDefinitionId)
                .and("SCOPE_TYPE_").is(scopeType)
                .and("SCOPE_ID_").isNull());
        delete(query);
    }

    @Override
    public EventSubscriptionEntity create() {
        // only allowed to create subclasses
        throw new UnsupportedOperationException();
    }
}
