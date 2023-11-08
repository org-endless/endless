package document.comment;

import org.flowable.engine.impl.persistence.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoCommentRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:29
 * <p>update 2023/9/22 16:29
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoCommentRepository extends MongoRepository<MongoComment, String> {

    List<CommentEntity> findByTaskIdAndTypeOrderByTimeDesc(String taskId, String type);

    List<CommentEntity> findByTypeOrderByTimeDesc(String type);

    List<CommentEntity> findByTaskIdOrderByTimeDesc(String taskId);

    List<CommentEntity> findByProcessInstanceIdOrderByTimeDesc(String processInstanceId);

    List<CommentEntity> findByProcessInstanceIdAndTypeOrderByTimeDesc(String processInstanceId, String type);

}
