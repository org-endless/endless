package document.historic.process.instance;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.engine.impl.persistence.entity.HistoricProcessInstanceEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoHistoricProcessInstance
 * <p>
 * <p>
 * <p>create 2023/8/19 3:42
 * <p>update 2023/8/19 3:42
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_PROCINST")
public class MongoHistoricProcessInstance extends HistoricProcessInstanceEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @Field("REV_")
    private Integer revision = 1;

    @Indexed(unique = true, background = true)
    @NonNull
    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Indexed(name = "ACT_IDX_HI_PRO_I_BUSKEY", background = true)
    @Field("BUSINESS_KEY_")
    private String businessKey;

    @NonNull
    @Field("PROC_DEF_ID_")
    private String processDefinitionId;

    @NonNull
    @Field("START_TIME_")
    private Date startTime;

    @Indexed(name = "ACT_IDX_HI_PRO_INST_END", background = true)
    @Field("END_TIME_")
    private Date endTime;

    @Field("DURATION_")
    private Long durationInMillis;

    @Field("START_USER_ID_")
    private String startUserId;

    @Field("START_ACT_ID_")
    private String startActivityId;

    @Field("END_ACT_ID_")
    private String endActivityId;

    @Indexed(name = "ACT_IDX_HI_PRO_SUPER_PROCINST", background = true)
    @Field("SUPER_PROCESS_INSTANCE_ID_")
    private String superProcessInstanceId;

    @Field("DELETE_REASON_")
    private String deleteReason;

    @Field("TENANT_ID_")
    private String tenantId = "";

    @Field("NAME_")
    private String name;

    @Field("CALLBACK_ID_")
    private String callbackId;

    @Field("CALLBACK_TYPE_")
    private String callbackType;

    @Field("REFERENCE_ID_")
    private String referenceId;

    @Field("REFERENCE_TYPE_")
    private String referenceType;

    @Field("PROPAGATED_STAGE_INST_ID_")
    private String propagatedStageInstanceId;

    @Field("BUSINESS_STATUS_")
    private String businessStatus;
}
