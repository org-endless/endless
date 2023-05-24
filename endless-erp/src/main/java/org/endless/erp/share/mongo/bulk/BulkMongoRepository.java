package org.endless.erp.share.mongo.bulk;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * BulkMongoRepository
 *
 * @author Deng Haozhi
 * @date 2023/4/8 16:31
 * @since 0.0.2
 */
public interface BulkMongoRepository {

    void upsert(List<Pair<Query, Update>> upsertList, Class<?> entityClass);

    void upsert(List<Pair<Query, Update>> upsertList, Integer bulkSize, Class<?> entityClass);
}