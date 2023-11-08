package document.comment;

import document.AbstractMongoDataManager;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.impl.persistence.entity.CommentEntity;
import org.flowable.engine.impl.persistence.entity.CommentEntityImpl;
import org.flowable.engine.impl.persistence.entity.data.CommentDataManager;
import org.flowable.engine.task.Comment;
import org.flowable.engine.task.Event;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * MongoCommentDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:29
 * <p>update 2023/9/22 16:29
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
@Import({MongoCommentRepository.class})
public class MongoCommentDataManager extends AbstractMongoDataManager<CommentEntity>
        implements CommentDataManager {

    public static final String COLLECTION_COMMENT = "ACT_HI_COMMENT";

    private final MongoCommentRepository repository;

    public MongoCommentDataManager(MongoOperations operations, MongoCommentRepository repository) {
        super(operations);
        this.repository = repository;
    }

    @Override
    public String getCollection() {
        return COLLECTION_COMMENT;
    }

    @Override
    public Class<CommentEntity> getEntityClass() {
        return CommentEntity.class;
    }

    @Override
    public Update getUpdateObject(CommentEntity entity) {
        return Update.update("TYPE_", entity.getType())
                .set("TIME_", entity.getTime())
                .set("USER_ID_", entity.getUserId())
                .set("TASK_ID_", entity.getTaskId())
                .set("PROC_INST_ID_", entity.getProcessInstanceId())
                .set("ACTION_", entity.getAction())
                .set("MESSAGE_", entity.getMessage())
                .set("FULL_MSG_", entity.getFullMessage());
    }

    @Override
    public List<Comment> findCommentsByTaskId(String taskId) {
        return repository.findByTaskIdAndTypeOrderByTimeDesc(taskId, "comment")
                .stream().map(entity -> (Comment) entity).toList();
    }

    @Override
    public List<Comment> findCommentsByTaskIdAndType(String taskId, String type) {
        return repository.findByTaskIdAndTypeOrderByTimeDesc(taskId, type)
                .stream().map(entity -> (Comment) entity).toList();
    }

    @Override
    public List<Comment> findCommentsByType(String type) {
        return repository.findByTypeOrderByTimeDesc(type)
                .stream().map(entity -> (Comment) entity).toList();
    }

    @Override
    public List<Event> findEventsByTaskId(String taskId) {
        return repository.findByTaskIdOrderByTimeDesc(taskId)
                .stream().map(entity -> (Event) entity).toList();
    }

    @Override
    public List<Event> findEventsByProcessInstanceId(String processInstanceId) {
        return repository.findByProcessInstanceIdOrderByTimeDesc(processInstanceId)
                .stream().map(entity -> (Event) entity).toList();
    }

    @Override
    public void deleteCommentsByTaskId(String taskId) {
        var query = Query.query(Criteria.where("TASK_ID_").is(taskId));
        delete(query);
    }

    @Override
    public void deleteCommentsByProcessInstanceId(String processInstanceId) {
        var query = Query.query(Criteria.where("PROC_INST_ID_").is(processInstanceId));
        delete(query);
    }

    @Override
    public void bulkDeleteCommentsForTaskIds(Collection<String> taskIds) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void bulkDeleteCommentsForProcessInstanceIds(Collection<String> processInstanceIds) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Comment> findCommentsByProcessInstanceId(String processInstanceId) {
        return repository.findByProcessInstanceIdOrderByTimeDesc(processInstanceId)
                .stream().map(entity -> (Comment) entity).toList();
    }

    @Override
    public List<Comment> findCommentsByProcessInstanceId(String processInstanceId, String type) {
        return repository.findByProcessInstanceIdAndTypeOrderByTimeDesc(processInstanceId, type)
                .stream().map(entity -> (Comment) entity).toList();
    }

    @Override
    public Comment findComment(String commentId) {
        return findById(commentId);
    }

    @Override
    public Event findEvent(String commentId) {
        return findById(commentId);
    }

    @Override
    public CommentEntity create() {
        return new CommentEntityImpl();
    }
}
