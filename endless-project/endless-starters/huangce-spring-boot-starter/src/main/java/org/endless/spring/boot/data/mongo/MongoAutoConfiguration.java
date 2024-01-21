package org.endless.spring.boot.data.mongo;

import org.endless.spring.boot.data.mongo.bulk.MongoBulkConfiguration;
import org.endless.spring.boot.data.mongo.mongo.MongoConfiguration;
import org.endless.spring.boot.data.mongo.reactive.ReactiveMongoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * MongoAutoConfiguration
 * <p>MongoDb自动配置类
 * <p>Mongodb automatic configuration class.
 * <p>
 * <p>create 2023/8/12 2:06
 * <p>update 2023/8/12 2:06
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@AutoConfiguration
@Import({MongoConfiguration.class,
        ReactiveMongoConfiguration.class,
        MongoBulkConfiguration.class})
public class MongoAutoConfiguration {
}
