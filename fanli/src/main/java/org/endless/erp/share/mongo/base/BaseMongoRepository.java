package org.endless.erp.share.mongo.base;

import org.endless.erp.share.mongo.bulk.BulkMongoRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * BaseMongoRepository
 *
 * <p>Mongo Repository模板类，自定义了一些方法
 *
 * <p>Mongo Repository template class, with some custom methods.
 *
 * <p>
 *
 * <p>create 2023/05/27 19:47
 *
 * <p>update 2023/05/27 19:47
 *
 * @author Deng Haozhi
 * @see MongoRepository
 * @see BulkMongoRepository
 * @since 0.0.3
 */
@NoRepositoryBean
public interface BaseMongoRepository<T, ID> extends MongoRepository<T, ID> {}
