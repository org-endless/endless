package org.endless.data.mongo.bulk;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * BulkMongoUpsertTaskImpl
 * <p>
 * <p>
 *
 * <p>create 2023/8/10 19:50
 * <p>update 2023/8/10 19:50
 *
 * @author Deng Haozhi
 * @since 0.1.1
 */
@Log4j2
@Component("bulkMongoUpsertTask")
public class BulkMongoUpsertTaskImpl implements BulkMongoUpsertTask {
    private final MongoOperations mongoOperations;

    public BulkMongoUpsertTaskImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    
    /**
     * 运行MongoDB批量更新插入操作异步任务
     *
     * <p>Run the Mongo DB bulk upsert asynchronous task.
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    public void run(List<Pair<Query, Update>> pairs, Class<?> entityClass) {

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
