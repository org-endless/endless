package document.event.subscription;

import org.flowable.eventsubscription.service.EventSubscriptionServiceConfiguration;
import org.flowable.eventsubscription.service.impl.persistence.entity.data.EventSubscriptionDataManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;

/**
 * MongoEventSubscriptionServiceConfiguration
 * <p>
 * <p>
 * <p>create 2023/9/18 12:35
 * <p>update 2023/9/18 12:35
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Import({MongoEventSubscriptionDataManager.class})
public class MongoEventSubscriptionServiceConfiguration extends EventSubscriptionServiceConfiguration {

    private final EventSubscriptionDataManager eventSubscriptionDataManager;

    public MongoEventSubscriptionServiceConfiguration(
            String engineName,
            @Qualifier("MongoEventSubscriptionDataManager") EventSubscriptionDataManager eventSubscriptionDataManager) {
        super(engineName);
        this.eventSubscriptionDataManager = eventSubscriptionDataManager;
    }


    @Override
    public void initDataManagers() {
    }

    @Override
    public EventSubscriptionDataManager getEventSubscriptionDataManager() {
        return eventSubscriptionDataManager;
    }

    @Override
    public EventSubscriptionServiceConfiguration setEventSubscriptionDataManager(EventSubscriptionDataManager eventSubscriptionDataManager) {
        return this;
    }
}
