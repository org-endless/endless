package document.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.task.api.DelegationState;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoTask
 * <p>
 * <p>
 * <p>create 2023/8/19 19:24
 * <p>update 2023/8/19 19:24
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_TASK")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_TASK_SCOPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_TASK_SUB_SCOPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_TASK_SCOPE_DEF", def = "{'SCOPE_DEFINITION_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoTask extends TaskEntityImpl {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "REV_")
    private Integer revision;

    @Field(name = "EXECUTION_ID_")
    private String executionId;

    @Field(name = "PROC_INST_ID_")
    private String processInstanceId;

    @Field(name = "PROC_DEF_ID_")
    private String processDefinitionId;

    @Field(name = "TASK_DEF_ID_")
    private String taskDefinitionId;

    @Field(name = "SCOPE_ID_")
    private String scopeId;

    @Field(name = "SUB_SCOPE_ID_")
    private String subScopeId;

    @Field(name = "SCOPE_TYPE_")
    private String scopeType;

    @Field(name = "SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;

    @Field(name = "PROPAGATED_STAGE_INST_ID_")
    private String propagatedStageInstanceId;

    @Field(name = "NAME_")
    private String name;

    @Field(name = "PARENT_TASK_ID_")
    private String parentTaskId;

    @Field(name = "DESCRIPTION_")
    private String description;

    @Field(name = "TASK_DEF_KEY_")
    private String taskDefinitionKey;

    @Field(name = "OWNER_")
    private String owner;

    @Field(name = "ASSIGNEE_")
    private String assignee;

    @Field(name = "DELEGATION_")
    private DelegationState delegationState;

    @Field(name = "PRIORITY_")
    private Integer priority;

    @Indexed(name = "ACT_IDX_TASK_CREATE", background = true)
    @Field(name = "CREATE_TIME_")
    private Date createTime;

    @Field(name = "DUE_DATE_")
    private Date dueDate;

    @Field(name = "CATEGORY_")
    private String category;

    @Field(name = "SUSPENSION_STATE_")
    private Integer suspensionState;

    @Field(name = "TENANT_ID_")
    private String tenantId = "";

    @Field(name = "FORM_KEY_")
    private String formKey;

    @Field(name = "CLAIM_TIME_")
    private Date claimTime;

    @Field(name = "IS_COUNT_ENABLED_")
    private Boolean isCountEnabled;

    @Field(name = "VAR_COUNT_")
    private Integer variableCount;

    @Field(name = "ID_LINK_COUNT_")
    private Integer identityLinkCount;

    @Field(name = "SUB_TASK_COUNT_")
    private Integer subTaskCount;
}
