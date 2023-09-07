package mongo.document.timer.job;

import org.endless.spring.boot.data.mongo.base.BaseMongoRepository;

/**
 * MongoTimerJobRepository
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/27 11:49
 * <p>update 2023/8/27 11:49
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoTimerJobRepository extends BaseMongoRepository<MongoTimerJobDocument, String> {

    MongoTimerJobDocument findByCorrelationId(String correlationId);
}
