package document.activity.instance;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.engine.impl.persistence.entity.ActivityInstanceEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoActivityInstance
 * <p>
 * <p>
 * <p>create 2023/8/17 20:53
 * <p>update 2023/8/17 20:53
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_ACTINST")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_RU_ACTI_PROC_ACT", def = "{'PROC_INST_ID_' : 1, 'ACT_ID_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_RU_ACTI_EXEC_ACT", def = "{'EXECUTION_ID_' : 1, 'ACT_ID_' : 1}", background = true)})
public class MongoActivityInstance extends ActivityInstanceEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @Field("REV_")
    private Integer revision = 1;

    @NonNull
    @Field("PROC_DEF_ID_")
    private String processDefinitionId;

    @Indexed(name = "ACT_IDX_RU_ACTI_PROC", background = true)
    @NonNull
    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Indexed(name = "ACT_IDX_RU_ACTI_EXEC", background = true)
    @NonNull
    @Field("EXECUTION_ID_")
    private String executionId;

    @NonNull
    @Field("ACT_ID_")
    private String activityId;

    @Indexed(name = "ACT_IDX_RU_ACTI_TASK", background = true)
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

    @Indexed(name = "ACT_IDX_RU_ACTI_START", background = true)
    @NonNull
    @Field("START_TIME_")
    private Date startTime;

    @Indexed(name = "ACT_IDX_RU_ACTI_END", background = true)
    @Field("END_TIME_")
    private Date endTime;

    @Field("DURATION_")
    private Long durationInMillis;

    @Field("TRANSACTION_ORDER_")
    private Integer transactionOrder;

    @Field("DELETE_REASON_")
    private String deleteReason;

    @Field("TENANT_ID_")
    private String tenantId = "";
}
