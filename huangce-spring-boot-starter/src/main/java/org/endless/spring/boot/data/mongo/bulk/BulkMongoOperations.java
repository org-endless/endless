package org.endless.spring.boot.data.mongo.bulk;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * BulkMongoOperations
 * <p>定义一些批量操作MongoDB的方法
 * <p>Define some methods for bulk operations on Mongo DB.
 * <p>
 * <p>create 2023/05/24 17:48
 * <p>update 2023/05/27 19:52
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
public interface BulkMongoOperations {

    /**
     * 单线程批量更新插入
     * <p>Single-threaded bulk upsert
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass);

    /**
     * 多线程批量更新插入
     *
     * <p>Multi-threaded bulk upsert.
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param pageSize    每线程处理的页面大小 (Page size per thread.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    void upsert(List<Pair<Query, Update>> pairs, Integer pageSize, Class<?> entityClass);
}
