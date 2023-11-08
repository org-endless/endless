package document.task;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoTaskRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:37
 * <p>update 2023/9/22 16:37
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoTaskRepository extends MongoRepository<MongoTask, String> {
}
