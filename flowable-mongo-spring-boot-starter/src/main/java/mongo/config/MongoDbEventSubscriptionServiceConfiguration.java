package mongo.config;

import mongo.persistence.manager.MongoDbEventSubscriptionDataManager;
import org.flowable.eventsubscription.service.EventSubscriptionServiceConfiguration;

public class MongoDbEventSubscriptionServiceConfiguration
        extends EventSubscriptionServiceConfiguration {

    public MongoDbEventSubscriptionServiceConfiguration(String engineName) {
        super(engineName);
    }

    @Override
    public void initDataManagers() {
        this.eventSubscriptionDataManager = new MongoDbEventSubscriptionDataManager();
    }
}
