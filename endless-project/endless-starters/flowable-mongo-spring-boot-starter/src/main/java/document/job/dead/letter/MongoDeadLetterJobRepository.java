package document.job.dead.letter;

import org.flowable.job.service.impl.persistence.entity.DeadLetterJobEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoDeadLetterJobRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:30
 * <p>update 2023/9/22 16:30
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoDeadLetterJobRepository extends MongoRepository<MongoDeadLetterJob, String> {

    DeadLetterJobEntity findByCorrelationId(String correlationId);

    List<DeadLetterJobEntity> findByExecutionId(String executionId);

    List<DeadLetterJobEntity> findByProcessInstanceId(String processInstanceId);

}
