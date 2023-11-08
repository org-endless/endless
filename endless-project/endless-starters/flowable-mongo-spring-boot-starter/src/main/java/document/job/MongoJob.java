package document.job;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.job.service.impl.persistence.entity.JobEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoJob
 * <p>
 * <p>
 * <p>create 2023/8/19 7:57
 * <p>update 2023/8/19 7:57
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_JOB")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_JOB_SCOPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_JOB_SUB_SCOPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_JOB_SCOPE_DEF", def = "{'SCOPE_DEFINITION_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoJob extends JobEntityImpl {

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

    @Field(name = "LOCK_EXP_TIME_")
    private Date lockExpirationTime;

    @Field(name = "LOCK_OWNER_")
    private String lockOwner;

    @Field(name = "EXCLUSIVE_")
    private Boolean exclusive;

    @Field(name = "EXECUTION_ID_")
    private String executionId;

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

    @Indexed(name = "ACT_IDX_JOB_CORRELATION_ID", background = true)
    @Field(name = "CORRELATION_ID_")
    private String correlationId;

    @Field(name = "RETRIES_")
    private Integer retries;

    @Indexed(name = "ACT_IDX_JOB_EXCEPTION", background = true)
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

    @Indexed(name = "ACT_IDX_JOB_CUSTOM_VAL_ID", background = true)
    @Field(name = "CUSTOM_VALUES_ID_")
    private ByteArrayRef customValuesByteArrayRef;

    @Field(name = "CREATE_TIME_")
    private Date createTime;

    @Field(name = "TENANT_ID_")
    private String tenantId = "";
}
