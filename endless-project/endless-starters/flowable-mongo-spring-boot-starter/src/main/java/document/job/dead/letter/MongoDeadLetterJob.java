package document.job.dead.letter;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.job.service.impl.persistence.entity.DeadLetterJobEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoDeadLetterJob
 * <p>
 * <p>
 * <p>create 2023/8/19 0:32
 * <p>update 2023/8/19 0:32
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_DEADLETTER_JOB")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_DJOB_SCOPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_DJOB_SUB_SCOPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_DJOB_SCOPE_DEF", def = "{'SCOPE_DEFINITION_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoDeadLetterJob extends DeadLetterJobEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @Field("REV_")
    private Integer revision;

    @Field("CATEGORY_")
    private String category;

    @NonNull
    @Field("TYPE_")
    private String jobType;

    @Field("EXCLUSIVE_")
    private Boolean exclusive;

    @Indexed(name = "ACT_IDX_DJOB_EXCEPTION", background = true)
    @Field("EXECUTION_ID_")
    private String executionId;

    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Field("PROC_DEF_ID_")
    private String processDefinitionId;

    @Field("ELEMENT_ID_")
    private String elementId;

    @Field("ELEMENT_NAME_")
    private String elementName;

    @Field("SCOPE_ID_")
    private String scopeId;

    @Field("SUB_SCOPE_ID_")
    private String subScopeId;

    @Field("SCOPE_TYPE_")
    private String scopeType;

    @Field("SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;

    @Indexed(name = "ACT_IDX_DJOB_CORRELATION_ID", background = true)
    @Field("CORRELATION_ID_")
    private String correlationId;

    @Field("EXCEPTION_STACK_ID_")
    private ByteArrayRef exceptionByteArrayRef;

    @Field("EXCEPTION_MSG_")
    private String exceptionMessage;

    @Field("DUEDATE_")
    private Date duedate;

    @Field("REPEAT_")
    private String repeat;

    @Field("HANDLER_TYPE_")
    private String jobHandlerType;

    @Field("HANDLER_CFG_")
    private String jobHandlerConfiguration;

    @Indexed(name = "ACT_IDX_DJOB_CUSTOM_VAL_ID", background = true)
    @Field("CUSTOM_VALUES_ID_")
    private ByteArrayRef customValuesByteArrayRef;

    @Field("CREATE_TIME_")
    private Date createTime;

    @Field("TENANT_ID_")
    private String tenantId = "";
}
