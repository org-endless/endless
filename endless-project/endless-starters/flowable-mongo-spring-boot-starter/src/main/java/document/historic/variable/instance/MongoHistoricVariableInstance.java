package document.historic.variable.instance;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.variable.api.types.VariableType;
import org.flowable.variable.service.impl.persistence.entity.HistoricVariableInstanceEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoHistoricVariableInstance
 * <p>
 * <p>
 * <p>create 2023/8/19 7:09
 * <p>update 2023/8/19 7:09
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_VARINST")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_HI_PROCVAR_NAME_TYPE", def = "{'NAME_' : 1, 'VAR_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_VAR_SCOPE_ID_TYPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_VAR_SUB_ID_TYPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoHistoricVariableInstance extends HistoricVariableInstanceEntityImpl {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "REV_")
    private Integer revision = 1;

    @Field(name = "PROC_INST_ID_")
    private String processInstanceId;

    @Field(name = "EXECUTION_ID_")
    private String executionId;

    @Field(name = "TASK_ID_")
    private String taskId;

    @NonNull
    @Field(name = "TASK_ID_")
    private String name;

    @Field(name = "VAR_TYPE_")
    private VariableType variableType;

    @Field(name = "SCOPE_ID_")
    private String scopeId;

    @Field(name = "SUB_SCOPE_ID_")
    private String subScopeId;

    @Field(name = "SCOPE_TYPE_")
    private String scopeType;

    @Field(name = "BYTEARRAY_ID_")
    private ByteArrayRef byteArrayRef;

    @Field(name = "DOUBLE_")
    private Double doubleValue;

    @Field(name = "LONG_")
    private Long longValue;

    @Field(name = "TEXT_")
    private String textValue;

    @Field(name = "TEXT2_")
    private String textValue2;

    @Field(name = "CREATE_TIME_")
    private Date createTime;

    @Field(name = "LAST_UPDATED_TIME_")
    private Date lastUpdatedTime;
}
