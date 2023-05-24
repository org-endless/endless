package org.endless.erp.share.mongo.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Deng Haozhi
 * @date 2023/3/27 14:19
 * @since 0.0.2
 */
@NoRepositoryBean
public interface MongoJpaRepository<T, ID> extends MongoRepository<T, ID> {
}