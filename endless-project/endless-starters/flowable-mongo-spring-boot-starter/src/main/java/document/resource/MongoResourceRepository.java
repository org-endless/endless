package document.resource;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoResourceRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:36
 * <p>update 2023/9/22 16:36
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoResourceRepository extends MongoRepository<MongoResource, String> {
}
