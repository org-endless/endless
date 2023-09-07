package mongo.document.historic.activity.instance;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * HistoricActivityInstance
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/19 2:43
 * <p>update 2023/8/19 2:43
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_ACTINST")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_HI_ACT_INST_PROCINST", def = "{'PROC_INST_ID_' : 1, 'ACT_ID_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_ACT_INST_EXEC", def = "{'EXECUTION_ID_' : 1, 'ACT_ID_' : 1}", background = true)})
public class HistoricActivityInstance {

    @Id
    @Field("ID_")
    private String id;

    @Field("REV_")
    private Integer revision = 1;

    @NonNull
    @Field("PROC_DEF_ID_")
    private String processDefinitionId;

    @NonNull
    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @NonNull
    @Field("EXECUTION_ID_")
    private String executionId;

    @NonNull
    @Field("ACT_ID_")
    private String activityId;

    @Field("TASK_ID_")
    private String taskId;

    @Field("CALL_PROC_INST_ID_")
    private String calledProcessInstanceId;

    @Field("ACT_NAME_")
    private String activityName;

    @NonNull
    @Field("ACT_TYPE_")
    private String activityType;

    @Field("ASSIGNEE_")
    private String assignee;

    @Indexed(name = "ACT_IDX_HI_ACT_INST_START", background = true)
    @NonNull
    @Field("START_TIME_")
    private Date startTime;

    @Indexed(name = "ACT_IDX_HI_ACT_INST_END", background = true)
    @Field("END_TIME_")
    private Date endTime;

    @Field("TRANSACTION_ORDER_")
    private Integer transactionOrder;

    @Field("DURATION_")
    private String durationInMillis;

    @Field("DELETE_REASON_")
    private String deleteReason;

    @Field("TENANT_ID_")
    private String tenantId = "";
}
