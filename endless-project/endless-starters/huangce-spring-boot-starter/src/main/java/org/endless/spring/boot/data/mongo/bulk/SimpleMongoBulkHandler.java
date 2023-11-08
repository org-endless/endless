package org.endless.spring.boot.data.mongo.bulk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * SimpleMongoBulkHandler
 * <p>定义MongoDB操作处理器
 * <p>Define the Mongo DB operation handler.
 * <p>
 * <p>create 2023/8/15 15:33
 * <p>update 2023/9/26 11:29
 *
 * @author Deng Haozhi
 * @see MongoBulkHandler
 * @since 0.0.4
 */
@Slf4j
public class SimpleMongoBulkHandler implements MongoBulkHandler {
    private final MongoOperations operations;

    public SimpleMongoBulkHandler(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
        log.debug(
                "Thread: "
                        + Thread.currentThread().getName()
                        + " upsert for >"
                        + entityClass.getName()
                        + "< executing");

        var bulkOps = operations.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass);

        try {
            bulkOps.upsert(pairs);
            var result = bulkOps.execute();

            log.debug(
                    "Thread: "
                            + Thread.currentThread().getName()
                            + " upsert for >"
                            + entityClass.getName()
                            + "< executed!");
            log.trace("The upsert result is: " + result);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void remove(List<Query> queries, Class<?> entityClass) {
        log.debug(
                "Thread: "
                        + Thread.currentThread().getName()
                        + " remove from >"
                        + entityClass.getName()
                        + "< executing");

        var bulkOps = operations.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass);

        try {
            bulkOps.remove(queries);
            var result = bulkOps.execute();

            log.debug(
                    "Thread: "
                            + Thread.currentThread().getName()
                            + " remove from >"
                            + entityClass.getName()
                            + "< executed!");
            log.trace("The remove result is: " + result);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
