package document.job.dead.letter;

import document.AbstractMongoDataManager;
import lombok.extern.slf4j.Slf4j;
import org.flowable.job.api.Job;
import org.flowable.job.service.impl.DeadLetterJobQueryImpl;
import org.flowable.job.service.impl.persistence.entity.DeadLetterJobEntity;
import org.flowable.job.service.impl.persistence.entity.DeadLetterJobEntityImpl;
import org.flowable.job.service.impl.persistence.entity.data.DeadLetterJobDataManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * MongoDeadLetterJobDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:29
 * <p>update 2023/9/22 16:29
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
@Import({MongoDeadLetterJobRepository.class})
public class MongoDeadLetterJobDataManager extends AbstractMongoDataManager<DeadLetterJobEntity>
        implements DeadLetterJobDataManager {

    public static final String COLLECTION_DEAD_LETTER_JOB = "ACT_RU_DEADLETTER_JOB";

    private final MongoDeadLetterJobRepository repository;


    public MongoDeadLetterJobDataManager(MongoOperations operations, MongoDeadLetterJobRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_DEAD_LETTER_JOB;
    }

    @Override
    public Class<DeadLetterJobEntity> getEntityClass() {
        return DeadLetterJobEntity.class;
    }

    @Override
    public Update getUpdateObject(DeadLetterJobEntity entity) {
        return Update.update("REV_", entity.getRevision())
                .set("CATEGORY_", entity.getCategory())
                .set("TYPE_", entity.getJobType())
                .set("EXCLUSIVE_", entity.isExclusive())
                .set("EXECUTION_ID_", entity.getExecutionId())
                .set("PROC_INST_ID_", entity.getProcessInstanceId())
                .set("PROC_DEF_ID_", entity.getProcessDefinitionId())
                .set("ELEMENT_ID_", entity.getElementId())
                .set("ELEMENT_NAME_", entity.getElementName())
                .set("SCOPE_ID_", entity.getScopeId())
                .set("SUB_SCOPE_ID_", entity.getSubScopeId())
                .set("SCOPE_TYPE_", entity.getScopeType())
                .set("SCOPE_DEFINITION_ID_", entity.getScopeDefinitionId())
                .set("CORRELATION_ID_", entity.getCorrelationId())
                .set("EXCEPTION_STACK_ID_", entity.getExceptionByteArrayRef())
                .set("EXCEPTION_MSG_", entity.getExceptionMessage())
                .set("DUEDATE_", entity.getDuedate())
                .set("REPEAT_", entity.getRepeat())
                .set("HANDLER_TYPE_", entity.getJobHandlerType())
                .set("HANDLER_CFG_", entity.getJobHandlerConfiguration())
                .set("CUSTOM_VALUES_ID_", entity.getCustomValuesByteArrayRef())
                .set("CREATE_TIME_", entity.getCreateTime())
                .set("TENANT_ID_", entity.getTenantId());
    }

    @Override
    public DeadLetterJobEntity findJobByCorrelationId(String correlationId) {
        return repository.findByCorrelationId(correlationId);
    }

    @Override
    public List<DeadLetterJobEntity> findJobsByExecutionId(String executionId) {
        return repository.findByExecutionId(executionId);
    }

    @Override
    public List<DeadLetterJobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        return repository.findByProcessInstanceId(processInstanceId);
    }

    @Override
    public List<Job> findJobsByQueryCriteria(DeadLetterJobQueryImpl jobQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public long findJobCountByQueryCriteria(DeadLetterJobQueryImpl jobQuery) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public DeadLetterJobEntity create() {
        return new DeadLetterJobEntityImpl();
    }
}
