import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import engin.MongoProcessEngineConfiguration;
import org.flowable.engine.ProcessEngine;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * FlowableAutoConfiguration
 * <p>Flowable 自动配置类
 * <p>
 * <p>
 * <p>create 2023/8/7 10:24
 * <p>update 2023/8/7 10:24
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@AutoConfiguration
public class FlowableAutoConfiguration {

    private final MongoClient mongoClient;

    private final MongoDatabase mongoDatabase;

    public FlowableAutoConfiguration(
            MongoClient mongoClient, MongoDatabase mongoDatabase) {
        this.mongoClient = mongoClient;
        this.mongoDatabase = mongoDatabase;
    }


    public @Bean ProcessEngine processEngine() {
        return new MongoProcessEngineConfiguration(mongoClient, mongoDatabase).setDisableIdmEngine(true).buildProcessEngine();
    }
}
