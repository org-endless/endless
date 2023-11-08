package document.attachment;

import document.AbstractMongoDataManager;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.impl.persistence.entity.AttachmentEntity;
import org.flowable.engine.impl.persistence.entity.AttachmentEntityImpl;
import org.flowable.engine.impl.persistence.entity.data.AttachmentDataManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * MongoAttachmentDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:22
 * <p>update 2023/9/22 16:22
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
@Import({MongoAttachmentRepository.class})
public class MongoAttachmentDataManager extends AbstractMongoDataManager<AttachmentEntity>
        implements AttachmentDataManager {

    public static final String COLLECTION_ATTACHMENT = "ACT_HI_ATTACHMENT";

    private final MongoAttachmentRepository repository;

    public MongoAttachmentDataManager(MongoOperations operations, MongoAttachmentRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_ATTACHMENT;
    }

    @Override
    public Class<AttachmentEntity> getEntityClass() {
        return AttachmentEntity.class;
    }

    @Override
    public Update getUpdateObject(AttachmentEntity entity) {
        return Update.update("REV_", entity.getRevision())
                .set("USER_ID_", entity.getUserId())
                .set("NAME_", entity.getName())
                .set("DESCRIPTION_", entity.getDescription())
                .set("TYPE_", entity.getType())
                .set("TASK_ID_", entity.getTaskId())
                .set("PROC_INST_ID_", entity.getProcessInstanceId())
                .set("URL_", entity.getUrl())
                .set("CONTENT_ID_", entity.getContentId())
                .set("TIME_", entity.getTime());
    }

    @Override
    public List<AttachmentEntity> findAttachmentsByProcessInstanceId(String processInstanceId) {
        return repository.findByProcessInstanceIdOrderByTimeDesc(processInstanceId);
    }

    @Override
    public List<AttachmentEntity> findAttachmentsByTaskId(String taskId) {
        return repository.findByTaskIdOrderByTimeDesc(taskId);
    }

    @Override
    public void bulkDeleteAttachmentsByTaskId(Collection<String> taskIds) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public AttachmentEntity create() {
        return new AttachmentEntityImpl();
    }
}
