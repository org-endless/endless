package org.endless.erp.share.mongo.bulk;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.thread.base.BaseAsyncTask;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

/**
 * BulkMongoUpsertTask
 *
 * <p>MongoDB批量更新插入操作异步任务
 *
 * <p>MongoDB bulk upsert asynchronous task.
 *
 * <p>
 *
 * <p>create 2023/05/25 21:51
 *
 * <p>update 2023/05/27 19:50
 *
 * @author Deng Haozhi
 * @see BaseAsyncTask
 * @since 0.0.3
 */
@Log4j2
@Component("BulkMongoTask")
public class BulkMongoUpsertTask implements BaseAsyncTask {

  private final MongoOperations mongoOperations;

  public BulkMongoUpsertTask(MongoOperations mongoOperations) {
    this.mongoOperations = mongoOperations;
  }

  /**
   * 运行MongoDB批量更新插入操作异步任务
   *
   * <p>Run the Mongo DB bulk upsert asynchronous task.
   *
   * @param pairs 定义Query和Update的List (Define a List of Query and Update.)
   * @param entityClass 进行操作的实体类 (The entity class that operates.)
   */
  @Override
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
