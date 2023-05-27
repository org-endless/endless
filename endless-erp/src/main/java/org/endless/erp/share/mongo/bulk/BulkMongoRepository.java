package org.endless.erp.share.mongo.bulk;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * BulkMongoRepository
 *
 * <p>create 2023/05/24 17:48
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
public interface BulkMongoRepository {

    void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass);

    void upsert(List<Pair<Query, Update>> pairs, Integer pageSize, Class<?> entityClass);
}