package document.task.historic.instance;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoHistoricTaskLogEntryRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:33
 * <p>update 2023/9/22 16:33
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoHistoricTaskInstanceRepository extends MongoRepository<MongoHistoricTaskInstance, String> {
}
