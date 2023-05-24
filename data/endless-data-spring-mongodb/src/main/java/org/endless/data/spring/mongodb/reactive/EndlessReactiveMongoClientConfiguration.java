package org.endless.data.spring.mongodb.reactive;

import com.mongodb.MongoClientSettings;
import org.endless.data.spring.mongodb.config.EndlessMongoClientProperties;
import org.endless.data.spring.mongodb.config.EndlessMongoClientSettingsBuilderCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoClientFactoryBean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

/**
 * Exception Translation Support for spring-data-mongodb-reactive
 * create 2023/01/26 16:30
 *
 * @author Deng Haozhi
 * @since 0.0.2
 */
@Configuration
// @EnableConfigurationProperties(EndlessMongoClientProperties.class)
public class EndlessReactiveMongoClientConfiguration {

    // public @Bean ReactiveMongoTemplate reactiveMongoTemplate(
    //         @Qualifier("mongoClientFactoryBean") ReactiveMongoClientFactoryBean reactiveMongoClientFactoryBean, MongoProperties properties) throws Exception {
    //     return new ReactiveMongoTemplate(reactiveMongoClientFactoryBean, properties.getDatabase());
    // }

    // public @Bean void reactiveMongoClient(
    //         @Qualifier("mongoClientFactoryBean") ReactiveMongoClientFactoryBean clientFactory, MongoClientSettings settings){
    // }

    // public @Bean ReactiveMongoClientFactoryBean mongoClientFactoryBean(
    //         @Qualifier("mongoBuilderCustomizer") EndlessMongoClientSettingsBuilderCustomizer builderCustomizer, MongoClientSettings settings) {
    //     ReactiveMongoClientFactoryBean clientFactory = new ReactiveMongoClientFactoryBean();
    //     MongoClientSettings.Builder builder = MongoClientSettings.builder(settings);
    //     builderCustomizer.customize(builder);
    //     clientFactory.setMongoClientSettings(settings);
    //     return clientFactory;
    // }

    public @Bean EndlessMongoClientSettingsBuilderCustomizer mongoBuilderCustomizer(
            @Qualifier("mongoClientProperties") EndlessMongoClientProperties clientProperties) {
        return new EndlessMongoClientSettingsBuilderCustomizer(clientProperties);
    }

    public @Bean EndlessMongoClientProperties mongoClientProperties() {
        return new EndlessMongoClientProperties();
    }
}