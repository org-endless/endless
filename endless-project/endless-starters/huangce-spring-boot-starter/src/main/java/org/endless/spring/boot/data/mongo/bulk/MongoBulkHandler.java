package org.endless.spring.boot.data.mongo.bulk;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * MongoBulkHandler
 * <p>定义MongoDB操作处理器
 * <p>Define the Mongo DB operation handler.
 * <p>
 * <p>create 2023/9/25 19:26
 * <p>update 2023/9/26 11:28
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoBulkHandler {

    /**
     * 批量更新插入
     * <p>Bulk update insert
     *
     * @param pairs       Query和Update类列表 (List of Query and Update.)
     * @param entityClass Mongo文档实体类 (The entity class of Mongo document.)
     */
    void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass);

    /**
     * 批量删除
     * <p>Bulk remove
     *
     * @param queries     Query类列表 (List of Query class.)
     * @param entityClass Mongo文档实体类 (The entity class of Mongo document.)
     */
    void remove(List<Query> queries, Class<?> entityClass);
}
