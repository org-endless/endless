package org.endless.spring.boot.data.mongo.reactive;

import org.springframework.context.annotation.Configuration;

/**
 * ReactiveMongoConfiguration
 * <p>
 * <p>
 * <p>create 2023/01/26 16:30
 * <p>update 2023/9/26 11:55
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Configuration
public class ReactiveMongoConfiguration {
    // TODO Mongo Reactive
    // public @Bean ReactiveMongoTemplate reactiveMongoTemplate(
    //         @Qualifier("mongoClientFactoryBean") ReactiveMongoClientFactoryBean reactiveMongoClientFactoryBean, MongoProperties properties) throws Exception {
    //     return new ReactiveMongoTemplate(reactiveMongoClientFactoryBean, properties.getDatabase());
    // }

    // public @Bean void reactiveMongoClient(
    //         @Qualifier("mongoClientFactoryBean") ReactiveMongoClientFactoryBean clientFactory, MongoClientSettings settings){
    // }

    // public @Bean ReactiveMongoClientFactoryBean mongoClientFactoryBean(
    //         @Qualifier("mongoBuilderCustomizer") CustomMongoClientSettingsBuilderCustomizer builderCustomizer, MongoClientSettings settings) {
    //     ReactiveMongoClientFactoryBean clientFactory = new ReactiveMongoClientFactoryBean();
    //     MongoClientSettings.Builder builder = MongoClientSettings.builder(settings);
    //     builderCustomizer.customize(builder);
    //     clientFactory.setMongoClientSettings(settings);
    //     return clientFactory;
    // }

    // public @Bean CustomMongoClientSettingsBuilderCustomizer mongoBuilderCustomizer(
    //         @Qualifier("mongoClientProperties") CustomMongoProperties clientProperties) {
    //     return new CustomMongoClientSettingsBuilderCustomizer(clientProperties);
    // }
    //
    // public @Bean CustomMongoProperties mongoClientProperties() {
    //     return new CustomMongoProperties();
    // }
}
