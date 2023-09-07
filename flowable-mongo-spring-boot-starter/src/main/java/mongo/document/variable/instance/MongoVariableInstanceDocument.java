package mongo.document.variable.instance;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.variable.api.types.VariableType;
import org.flowable.variable.service.impl.persistence.entity.VariableInstanceEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MongoVariableInstanceDocument
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/19 19:56
 * <p>update 2023/8/19 19:56
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_VARIABLE")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_RU_VAR_SCOPE_ID_TYPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_RU_VAR_SUB_ID_TYPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoVariableInstanceDocument extends VariableInstanceEntityImpl {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "REV_")
    private Integer revision;

    @NonNull
    @Field(name = "TYPE_")
    private VariableType type;

    @NonNull
    @Field(name = "NAME_")
    private String name;

    @Field(name = "EXECUTION_ID_")
    private String executionId;

    @Field(name = "PROC_INST_ID_")
    private String processInstanceId;

    @Field(name = "TASK_ID_")
    private String taskId;

    @Field(name = "SCOPE_ID_")
    private String scopeId;

    @Field(name = "SUB_SCOPE_ID_")
    private String subScopeId;

    @Field(name = "SCOPE_TYPE_")
    private String scopeType;

    @Indexed(name = "ACT_IDX_VAR_BYTEARRAY", background = true)
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
}
