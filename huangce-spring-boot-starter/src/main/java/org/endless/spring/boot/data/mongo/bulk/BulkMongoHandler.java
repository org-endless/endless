package org.endless.spring.boot.data.mongo.bulk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * BulkMongoHandler
 * <p>定义MongoDB操作处理器
 * <p>Define the Mongo DB operation handler.
 * <p>
 * <p>create 2023/8/15 15:33
 * <p>update 2023/8/15 15:33
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
public class BulkMongoHandler {
    private final MongoOperations mongoOperations;

    public BulkMongoHandler(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     * 批量更新插入
     * <p>Bulk update insert
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    public void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
        log.debug(
                "Thread: "
                        + Thread.currentThread().getName()
                        + " upsert for >"
                        + entityClass.getName()
                        + "< executing");

        var bulkOps = mongoOperations.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass);

        try {
            bulkOps.upsert(pairs);
            var result = bulkOps.execute();

            log.debug(
                    "Thread: "
                            + Thread.currentThread().getName()
                            + " upsert for >"
                            + entityClass.getName()
                            + "< executed!");
            log.trace("The result is: " + result);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
