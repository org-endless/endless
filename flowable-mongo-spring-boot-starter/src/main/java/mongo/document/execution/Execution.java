package mongo.document.execution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Execution
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/19 1:46
 * <p>update 2023/8/19 1:46
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_EXECUTION")
public class Execution {

    @Id
    @Field("ID_")
    private String id;

    @Field("REV_")
    private Integer revision;

    @Indexed(name = "ACT_IDX_EXE_PROCINST", background = true)
    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Indexed(name = "ACT_IDX_EXEC_BUSKEY", background = true)
    @Field("BUSINESS_KEY_")
    private String businessKey;

    @Indexed(name = "ACT_IDX_EXE_PARENT", background = true)
    @Field("PARENT_ID_")
    private String parentId;

    @Indexed(name = "ACT_IDX_EXE_PROCDEF", background = true)
    @Field("PROC_DEF_ID_")
    private String processDefinitionId;

    @Indexed(name = "ACT_IDX_EXE_SUPER", background = true)
    @Field("SUPER_EXEC_")
    private String superExecutionId;

    @Indexed(name = "ACT_IDX_EXEC_ROOT", background = true)
    @Field("ROOT_PROC_INST_ID_")
    private String rootProcessInstanceId;

    @Field("ACT_ID_")
    private String activityId;

    @Field("IS_ACTIVE_")
    private Boolean isActive;

    @Field("IS_CONCURRENT_")
    private Boolean isConcurrent;

    @Field("IS_SCOPE_")
    private Boolean isScope;

    @Field("IS_EVENT_SCOPE_")
    private Boolean isEventScope;

    @Field("IS_MI_ROOT_")
    private Boolean isMultiInstanceRoot;

    @Field("SUSPENSION_STATE_")
    private Integer suspensionState;

    @Field("CACHED_ENT_STATE_")
    private Integer cachedEntState;

    @Field("TENANT_ID_")
    private String tenantId = "";

    @Field("NAME_")
    private String name;

    @Field("START_ACT_ID_")
    private String startActivityId;

    @Field("START_TIME_")
    private Date startTime;

    @Field("START_USER_ID_")
    private String startUserId;

    @Field("LOCK_TIME_")
    private Date lockTime;

    @Field("LOCK_OWNER_")
    private String lockOwner;

    @Field("IS_COUNT_ENABLED_")
    private Boolean isCountEnabled;

    @Field("EVT_SUBSCR_COUNT_")
    private Integer eventSubscriptionCount;

    @Field("TASK_COUNT_")
    private Integer taskCount;

    @Field("JOB_COUNT_")
    private Integer jobCount;

    @Field("TIMER_JOB_COUNT_")
    private Integer timerJobCount;

    @Field("SUSP_JOB_COUNT_")
    private Integer suspendedJobCount;

    @Field("DEADLETTER_JOB_COUNT_")
    private Integer deadLetterJobCount;

    @Field("EXTERNAL_WORKER_JOB_COUNT_")
    private Integer externalWorkerJobCount;

    @Field("VAR_COUNT_")
    private Integer variableCount;

    @Field("ID_LINK_COUNT_")
    private Integer identityLinkCount;

    @Field("CALLBACK_ID_")
    private String callbackId;

    @Field("CALLBACK_TYPE_")
    private String callbackType;

    @Indexed(name = "ACT_IDX_EXEC_REF_ID_", background = true)
    @Field("REFERENCE_ID_")
    private String referenceId;

    @Field("REFERENCE_TYPE_")
    private String referenceType;

    @Field("PROPAGATED_STAGE_INST_ID_")
    private String propagatedStageInstanceId;

    @Field("BUSINESS_STATUS_")
    private String businessStatus;
}
