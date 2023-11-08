package config;

import org.flowable.eventsubscription.service.EventSubscriptionServiceConfiguration;
import persistence.manager.MongoDbEventSubscriptionDataManager;

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
