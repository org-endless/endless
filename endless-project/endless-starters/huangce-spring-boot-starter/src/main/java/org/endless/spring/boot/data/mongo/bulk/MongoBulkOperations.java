package org.endless.spring.boot.data.mongo.bulk;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * MongoBulkOperations
 * <p>定义一些批量操作MongoDB的方法
 * <p>Define some methods for bulk operations on Mongo DB.
 * <p>
 * <p>create 2023/05/24 17:48
 * <p>update 2023/9/26 11:28
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoBulkOperations {

    /**
     * 单线程批量更新插入
     * <p>Single-threaded bulk upsert
     *
     * @param pairs       Query和Update类列表 (List of Query and Update.)
     * @param entityClass Mongo文档实体类 (The entity class of Mongo document.)
     */
    void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass);

    /**
     * 多线程批量更新插入
     * <p>Multi-threaded bulk upsert.
     *
     * @param pairs       Query和Update类列表 (List of Query and Update.)
     * @param pageSize    每线程处理的页面大小 (Page size per thread.)
     * @param entityClass Mongo文档实体类 (The entity class of Mongo document.)
     */
    void upsert(List<Pair<Query, Update>> pairs, Integer pageSize, Class<?> entityClass);

    /**
     * 单线程批量删除
     * <p>Single-threaded bulk remove
     *
     * @param queries     Query类列表 (List of Query class.)
     * @param entityClass Mongo文档实体类 (The entity class of Mongo document.)
     */
    void remove(List<Query> queries, Class<?> entityClass);

    /**
     * 多线程批量更新插入
     * <p>Multi-threaded bulk upsert.
     *
     * @param queries     Query类列表 (List of Query class.)
     * @param pageSize    每线程处理的页面大小 (Page size per thread.)
     * @param entityClass Mongo文档实体类 (The entity class of Mongo document.)
     */
    void remove(List<Query> queries, Integer pageSize, Class<?> entityClass);
}
