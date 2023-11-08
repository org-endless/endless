package document.event.subscription;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.eventsubscription.service.impl.persistence.entity.EventSubscriptionEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoEventSubscription
 * <p>
 * <p>
 * <p>create 2023/8/19 0:41
 * <p>update 2023/8/19 0:41
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_EVENT_SUBSCR")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_EVENT_SUBSCR_SCOPEREF_", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoEventSubscription extends EventSubscriptionEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @Field("REV_")
    private Integer revision;

    @Field("ACTIVITY_ID_")
    private String activityId;

    @Indexed(name = "ACT_IDX_EVENT_SUBSCR", background = true)
    @Field("EXECUTION_ID_")
    private String executionId;

    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Field("PROC_DEF_ID_")
    private String processDefinitionId;

    @Field("TENANT_ID_")
    private String tenantId = "";

    @Field("SCOPE_ID_")
    private String scopeId;

    @Field("SUB_SCOPE_ID_")
    private String subScopeId;

    @Field("SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;

    @Field("SCOPE_TYPE_")
    private String scopeType;

    @NonNull
    @Field("EVENT_TYPE_")
    private String eventType;

    @Field("EVENT_NAME_")
    private String eventName;

    @Indexed(name = "ACT_IDX_EVENT_SUBSCR_CONFIG_", background = true)
    @Field("CONFIGURATION_")
    private String configuration;

    @NonNull
    @Field("CREATED_")
    private Date created;

    @Field("LOCK_TIME_")
    private Date lockTime;

    @Field("LOCK_OWNER_")
    private String lockOwner;
}
