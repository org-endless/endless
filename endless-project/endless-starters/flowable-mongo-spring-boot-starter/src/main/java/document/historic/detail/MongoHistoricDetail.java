package document.historic.detail;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.engine.impl.persistence.entity.HistoricDetailEntityImpl;
import org.flowable.variable.api.types.VariableType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoHistoricDetail
 * <p>
 * <p>
 * <p>create 2023/8/19 2:51
 * <p>update 2023/8/19 2:51
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_DETAIL")
public class MongoHistoricDetail extends HistoricDetailEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @NonNull
    @Field("TYPE_")
    private String type;

    @Indexed(name = "ACT_IDX_HI_DETAIL_PROC_INST", background = true)
    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Field("EXECUTION_ID_")
    private String executionId;

    @Indexed(name = "ACT_IDX_HI_DETAIL_TASK_ID", background = true)
    @Field("TASK_ID_")
    private String taskId;

    @Indexed(name = "ACT_IDX_HI_DETAIL_ACT_INST", background = true)
    @Field("ACT_INST_ID_")
    private String activityInstanceId;

    @Indexed(name = "ACT_IDX_HI_DETAIL_NAME", background = true)
    @NonNull
    @Field("NAME_")
    private String name;

    @Field("VAR_TYPE_")
    private VariableType variableType;

    @Field("REV_")
    private Integer revision;

    @Indexed(name = "ACT_IDX_HI_DETAIL_TIME", background = true)
    @NonNull
    @Field("TIME_")
    private Date time;

    @Field("BYTEARRAY_ID_")
    private ByteArrayRef byteArrayRef;

    @Field("DOUBLE_")
    private Double doubleValue;

    @Field("LONG_")
    private Long longValue;

    @Field("TEXT_")
    private Long textValue;

    @Field("TEXT2_")
    private Long textValue2;
}
