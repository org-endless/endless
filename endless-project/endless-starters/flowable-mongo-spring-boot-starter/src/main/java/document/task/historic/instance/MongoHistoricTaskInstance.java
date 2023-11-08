package document.task.historic.instance;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.task.service.impl.persistence.entity.HistoricTaskInstanceEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoHistoricTaskLogEntry
 * <p>
 * <p>
 * <p>create 2023/8/19 4:19
 * <p>update 2023/8/19 4:19
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_TASKINST")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_HI_TASK_SCOPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_TASK_SUB_SCOPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_TASK_SCOPE_DEF", def = "{'SCOPE_DEFINITION_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoHistoricTaskInstance extends HistoricTaskInstanceEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @Field("REV_")
    private Integer revision = 1;

    @Field("PROC_DEF_ID_")
    private String processDefinitionId;

    @Field("TASK_DEF_ID_")
    private String taskDefinitionId;

    @Field("TASK_DEF_KEY_")
    private String taskDefinitionKey;

    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Field("EXECUTION_ID_")
    private String executionId;

    @Field("SCOPE_ID_")
    private String scopeId;

    @Field("SUB_SCOPE_ID_")
    private String subScopeId;

    @Field("SCOPE_TYPE_")
    private String scopeType;

    @Field("SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;

    @Field("PROPAGATED_STAGE_INST_ID_")
    private String propagatedStageInstanceId;

    @Field("PARENT_TASK_ID_")
    private String parentTaskId;

    @Field("NAME_")
    private String name;

    @Field("DESCRIPTION_")
    private String description;

    @Field("OWNER_")
    private String owner;

    @Field("ASSIGNEE_")
    private String assignee;

    @NonNull
    @Field("START_TIME_")
    private Date createTime;

    @Field("CLAIM_TIME_")
    private Date claimTime;

    @Field("END_TIME_")
    private Date endTime;

    @Field("DURATION_")
    private Long durationInMillis;

    @Field("DELETE_REASON_")
    private String deleteReason;

    @Field("PRIORITY_")
    private Integer priority;

    @Field("DUE_DATE_")
    private Date dueDate;

    @Field("FORM_KEY_")
    private String formKey;

    @Field("CATEGORY_")
    private String category;

    @Field("TENANT_ID_")
    private String tenantId = "";

    @Field("LAST_UPDATED_TIME_")
    private Date lastUpdateTime;
}
