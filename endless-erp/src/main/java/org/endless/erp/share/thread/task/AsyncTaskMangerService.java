package org.endless.erp.share.thread.task;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;



/**
 * AsyncTaskMangerService
 *
 * @author Deng Haozhi
 * @date 2023/5/24 12:15
 * @since 0.0.3
 */
@Service
public class AsyncTaskMangerService {

    private final MongoTemplate mongoTemplate;

    private final AsyncTaskMangerJpaRepository asyncTaskMangerJpaRepository;

    public AsyncTaskMangerService(MongoTemplate mongoTemplate, AsyncTaskMangerJpaRepository asyncTaskMangerJpaRepository) {
        this.mongoTemplate = mongoTemplate;
        this.asyncTaskMangerJpaRepository = asyncTaskMangerJpaRepository;
    }

    public void upsert(String taskId, AsyncTaskManager.Status status) {

        Query query = Query.query()
        var task = asyncTaskMangerJpaRepository.findById(taskId);

        if (task.isPresent()) {

        }






        mongoTemplate.upsert();
    }
}