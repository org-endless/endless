package org.endless.erp.share.thread.task;

import org.endless.erp.share.mongo.jpa.MongoJpaRepository;

/**
 * AsyncTaskMangerJpaRepository
 *
 * @author Deng Haozhi
 * @date 2023/5/24 12:18
 * @since 0.0.3
 */
public interface AsyncTaskMangerJpaRepository extends MongoJpaRepository<AsyncTaskManager, String> {
}