package document.job.timer;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoTimerJobRepository
 * <p>
 * <p>
 * <p>create 2023/8/27 11:49
 * <p>update 2023/8/27 11:49
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoTimerJobRepository extends MongoRepository<MongoTimerJob, String> {
}
