package org.endless.spring.boot.data.mongo.bulk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * SimpleMongoBulkTask
 * <p>MongoDB批量更新插入操作异步任务
 * <p>MongoDB bulk upsert asynchronous task.
 * <p>
 * <p>create 2023/8/10 19:50
 * <p>update 2023/9/26 11:30
 *
 * @author Deng Haozhi
 * @see MongoBulkTask
 * @since 0.0.4
 */
@Slf4j
public class SimpleMongoBulkTask implements MongoBulkTask {

    private final MongoBulkHandler bulkHandler;

    public SimpleMongoBulkTask(MongoBulkHandler bulkHandler) {
        this.bulkHandler = bulkHandler;
    }

    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
        bulkHandler.upsert(pairs, entityClass);
    }

    @Override
    public void remove(List<Query> queries, Class<?> entityClass) {
        bulkHandler.remove(queries, entityClass);
    }
}
