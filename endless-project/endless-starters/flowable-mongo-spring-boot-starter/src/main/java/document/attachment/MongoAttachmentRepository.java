package document.attachment;

import org.flowable.engine.impl.persistence.entity.AttachmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoAttachmentRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:22
 * <p>update 2023/9/22 16:22
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoAttachmentRepository extends MongoRepository<MongoAttachment, String> {

    List<AttachmentEntity> findByProcessInstanceIdOrderByTimeDesc(String processInstanceId);

    List<AttachmentEntity> findByTaskIdOrderByTimeDesc(String taskId);
}
