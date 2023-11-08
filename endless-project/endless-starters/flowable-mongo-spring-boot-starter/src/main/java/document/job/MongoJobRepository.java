package document.job;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoJobRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:35
 * <p>update 2023/9/22 16:35
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoJobRepository extends MongoRepository<MongoJob, String> {
}
