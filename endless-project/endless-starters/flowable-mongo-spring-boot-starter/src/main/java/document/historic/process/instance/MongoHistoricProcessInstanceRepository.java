package document.historic.process.instance;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoHistoricProcessInstanceRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:32
 * <p>update 2023/9/22 16:32
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoHistoricProcessInstanceRepository extends MongoRepository<MongoHistoricProcessInstance, String> {
}
