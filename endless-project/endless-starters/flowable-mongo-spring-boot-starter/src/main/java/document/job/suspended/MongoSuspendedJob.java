package document.job.suspended;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.job.service.impl.persistence.entity.SuspendedJobEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoSuspendedJob
 * <p>
 * <p>create 2023/9/26 15:56
 * <p>update 2023/9/26 15:56
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_SUSPENDED_JOB")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_SJOB_SCOPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_SJOB_SUB_SCOPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_SJOB_SCOPE_DEF", def = "{'SCOPE_DEFINITION_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoSuspendedJob extends SuspendedJobEntityImpl {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "REV_")
    private Integer revision;

    @Field(name = "CATEGORY_")
    private String category;

    @NonNull
    @Field(name = "TYPE_")
    private String jobType;

    @Field(name = "EXCLUSIVE_")
    private Boolean exclusive;

    @Field(name = "EXECUTION_ID_")
    private Boolean executionId;

    @Field(name = "PROC_INST_ID_")
    private String processInstanceId;

    @Field(name = "PROC_DEF_ID_")
    private String processDefinitionId;

    @Field(name = "ELEMENT_ID_")
    private String elementId;

    @Field(name = "ELEMENT_NAME_")
    private String elementName;

    @Field(name = "SCOPE_ID_")
    private String scopeId;

    @Field(name = "SUB_SCOPE_ID_")
    private String subScopeId;

    @Field(name = "SCOPE_TYPE_")
    private String scopeType;

    @Field(name = "SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;

    @Indexed(name = "ACT_IDX_SUSPENDED_JOB_CORRELATION_ID", background = true)
    @Field(name = "CORRELATION_ID_")
    private String correlationId;

    @Field(name = "RETRIES_")
    private Integer retries;

    @Indexed(name = "ACT_IDX_SUSPENDED_JOB_EXCEPTION_STACK_ID", background = true)
    @Field(name = "EXCEPTION_STACK_ID_")
    private ByteArrayRef exceptionByteArrayRef;

    @Field(name = "EXCEPTION_MSG_")
    private String exceptionMessage;

    @Field(name = "DUEDATE_")
    private Date duedate;

    @Field(name = "REPEAT_")
    private String repeat;

    @Field(name = "HANDLER_TYPE_")
    private String jobHandlerType;

    @Field(name = "HANDLER_CFG_")
    private String jobHandlerConfiguration;

    @Indexed(name = "ACT_IDX_SUSPENDED_JOB_CUSTOM_VALUES_ID", background = true)
    @Field(name = "CUSTOM_VALUES_ID_")
    private ByteArrayRef customValuesByteArrayRef;

    @Field(name = "CREATE_TIME_")
    private Date createTime;

    @Field(name = "TENANT_ID_")
    private String tenantId = "";
}
