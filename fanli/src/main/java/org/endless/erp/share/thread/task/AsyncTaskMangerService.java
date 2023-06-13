package org.endless.erp.share.thread.task;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.util.date.DateFormatter;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * AsyncTaskMangerService
 *
 * @author Deng Haozhi
 * @date 2023/5/24 12:15
 * @since 0.0.3
 */
@Log4j2
@Service
public class AsyncTaskMangerService {

  private final MongoOperations mongoOperations;

  private final AsyncTaskMangerRepositoryBase asyncTaskMangerJpaRepository;

  public AsyncTaskMangerService(
      MongoOperations mongoOperations, AsyncTaskMangerRepositoryBase asyncTaskMangerJpaRepository) {
    this.mongoOperations = mongoOperations;
    this.asyncTaskMangerJpaRepository = asyncTaskMangerJpaRepository;
  }

  public void create(String id, AsyncTaskManager.Status status) {

    if (!id.contains("_")) {
      throw new IllegalArgumentException("WRONG format of ID! It need \"industryId_asyncTaskId\".");
    }

    if (asyncTaskMangerJpaRepository.existsById(id)) {

      var result =
          mongoOperations
              .update(AsyncTaskManager.class)
              .matching(
                  Query.query(
                      Criteria.where("id").is(id).and("statue").is(AsyncTaskManager.Status.exit)))
              .apply(
                  Update.update("status", status)
                      .set("updateDateTime", DateFormatter.nowIso())
                      .set("updateTimeStamp", System.currentTimeMillis()))
              .findAndModify();
      if (result.isEmpty()) {
        throw new TaskRejectedException("There already has a SAME task running!");
      }
    } else {
      mongoOperations
          .update(AsyncTaskManager.class)
          .matching(Query.query(Criteria.where("id").is(id)))
          .apply(
              Update.update("asyncTaskId", id.split("_")[1])
                  .set("industryId", id.split("_")[0])
                  .set("status", status)
                  .set("createDateTime", DateFormatter.nowIso())
                  .set("createTimeStamp", System.currentTimeMillis())
                  .set("updateDateTime", DateFormatter.nowIso())
                  .set("updateTimeStamp", System.currentTimeMillis()))
          .upsert();
    }
  }

  public void ready() {}

  public void running() {}

  public void waiting() {}

  public void exit() {}
}
