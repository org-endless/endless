package document.job.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayRef;
import org.flowable.job.service.impl.persistence.entity.HistoryJobEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoHistoryJob
 * <p>
 * <p>create 2023/9/26 17:30
 * <p>update 2023/9/26 17:30
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RU_HISTORY_JOB")
public class MongoHistoryJob extends HistoryJobEntityImpl {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "REV_")
    private Integer revision;

    @Field(name = "LOCK_EXP_TIME_")
    private Date lockExpirationTime;

    @Field(name = "LOCK_OWNER_")
    private String lockOwner;

    @Field(name = "RETRIES_")
    private Integer retries;

    @Field(name = "EXCEPTION_STACK_ID_")
    private ByteArrayRef exceptionByteArrayRef;

    @Field(name = "EXCEPTION_MSG_")
    private String exceptionMessage;

    @Field(name = "HANDLER_TYPE_")
    private String jobHandlerType;

    @Field(name = "HANDLER_CFG_")
    private String jobHandlerConfiguration;

    @Field(name = "CUSTOM_VALUES_ID_")
    private ByteArrayRef customValuesByteArrayRef;

    @Field(name = "ADV_HANDLER_CFG_ID_")
    private ByteArrayRef advancedJobHandlerConfigurationByteArrayRef;

    @Field(name = "CREATE_TIME_")
    private Date createTime;

    @Field(name = "SCOPE_TYPE_")
    private String scopeType;

    @Field(name = "TENANT_ID_")
    private String tenantId = "";
}
