package document.job.suspended;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoSuspendedJobRepository
 * <p>
 * <p>create 2023/9/26 15:58
 * <p>update 2023/9/26 15:58
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoSuspendedJobRepository extends MongoRepository<MongoSuspendedJob, String> {
}
