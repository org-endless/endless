package document.job.history;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoHistoryJobRepository
 * <p>
 * <p>
 * <p>
 * <p>create 2023/9/26 17:31
 * <p>update 2023/9/26 17:31
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoHistoryJobRepository extends MongoRepository<MongoHistoryJob, String> {
}
