package document.task.historic.log.entry;

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
public interface MongoHistoricTaskLogEntryRepository extends MongoRepository<MongoHistoricTaskLogEntry, String> {
}
