package document.event.subscription;

import org.flowable.eventsubscription.api.EventSubscription;
import org.flowable.eventsubscription.service.impl.persistence.entity.EventSubscriptionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * MongoEventSubscriptionRepository
 * <p>
 * <p>
 * <p>create 2023/9/18 14:39
 * <p>update 2023/9/18 14:39
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoEventSubscriptionRepository extends MongoRepository<MongoEventSubscription, String> {

    long countByProcessInstanceId(String processInstanceId);

    List<EventSubscription> findByProcessInstanceId(String processInstanceId);

    List<EventSubscriptionEntity> findByProcessInstanceIdAndEventNameAndEventType(String processInstanceId, String eventName, String eventType);

    List<EventSubscriptionEntity> findByScopeIdAndScopeTypeAndEventNameAndEventType(String scopeId, String scopeType, String eventName, String eventType);

    List<EventSubscriptionEntity> findByEventNameAndExecutionIdAndEventType(String eventName, String executionId, String eventType);

    List<EventSubscriptionEntity> findByExecutionIdAndEventType(String executionId, String eventType);

    List<EventSubscriptionEntity> findByProcessInstanceIdAndEventType(String processInstanceId, String eventType);

    List<EventSubscriptionEntity> findByProcessInstanceIdAndActivityIdAndEventType(String processInstanceId, String activityId, String eventType);

    List<EventSubscriptionEntity> findByExecutionId(String executionId);

    List<EventSubscriptionEntity> findBySubScopeId(String subScopeId);

    @Query(value = "{'EVENT_TYPE_': ?0, 'PROC_DEF_ID_': ?1, 'TENANT_ID_': {$in: [?2, '']}, 'EXECUTION_ID_': ?3, 'PROC_INST_ID_': ?4}")
    List<EventSubscriptionEntity> findByEventTypeAndProcessDefinitionIdAndTenantIdAndExecutionIdAndProcessInstanceId(String eventType, String processDefinitionId, String tenantId, String executionId, String processInstanceId);

    List<EventSubscriptionEntity> findByScopeIdAndEventType(String scopeId, String eventType);

    @Query(value = "{'EVENT_TYPE_': ?0, 'EVENT_NAME_': ?1, 'TENANT_ID_': {$in: [?2 , '']}}")
    List<EventSubscriptionEntity> findByEventTypeAndEventNameAndTenantId(String eventType, String eventName, String tenantId);

    List<EventSubscriptionEntity> findByEventTypeAndEventNameAndExecutionId(String executionId, String eventType, String eventName);

    @Query(value = "{'EVENT_NAME_': ?0, 'TENANT_ID_': {$in: [?1, '']}, 'EXECUTION_ID_': ?2, 'EVENT_TYPE_': ?3}")
    EventSubscriptionEntity findByEventNameAndTenantIdAndExecutionIdAndEventType(String eventName, String tenantId, String executionId, String eventType);
}
