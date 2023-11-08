package document.deployment;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDeploymentRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:30
 * <p>update 2023/9/22 16:30
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoDeploymentRepository extends MongoRepository<MongoDeployment, String> {
}
