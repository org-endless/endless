package document.job.external.worker;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoExternalWorkerJobRepository
 * <p>
 * <p>create 2023/9/26 17:48
 * <p>update 2023/9/26 17:48
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoExternalWorkerJobRepository extends MongoRepository<MongoExternalWorkerJob, String> {
}
