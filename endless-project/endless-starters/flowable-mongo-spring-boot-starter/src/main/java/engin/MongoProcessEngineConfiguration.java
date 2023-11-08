package engin;

import config.MongoDbEventSubscriptionServiceConfiguration;
import document.job.MongoJobServiceConfiguration;
import document.task.MongoTaskServiceConfiguration;
import lombok.Getter;
import org.flowable.common.engine.api.scope.ScopeTypes;
import org.flowable.common.engine.impl.interceptor.CommandInterceptor;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.eventsubscription.service.EventSubscriptionServiceConfiguration;
import org.flowable.job.service.JobServiceConfiguration;
import org.flowable.task.service.TaskServiceConfiguration;
import org.springframework.context.annotation.Import;
import schema.MongoProcessSchemaManager;

/**
 * MongoProcessEngineConfiguration
 * <p>
 * <p>
 * <p>
 * <p>create 2023/9/18 12:09
 * <p>update 2023/9/18 12:09
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Import({MongoJobServiceConfiguration.class})
public class MongoProcessEngineConfiguration extends ProcessEngineConfigurationImpl {

    @Getter
    private MongoProcessSchemaManager processSchemaManager;

    @Override
    public CommandInterceptor createTransactionInterceptor() {
        return null;
    }

    @Override
    protected EventSubscriptionServiceConfiguration
    instantiateEventSubscriptionServiceConfiguration() {
        return new MongoDbEventSubscriptionServiceConfiguration(ScopeTypes.BPMN);
    }

    @Override
    protected JobServiceConfiguration instantiateJobServiceConfiguration() {
        return new MongoJobServiceConfiguration(ScopeTypes.BPMN);
    }

    @Override
    protected TaskServiceConfiguration instantiateTaskServiceConfiguration() {
        return new MongoTaskServiceConfiguration(ScopeTypes.BPMN);
    }

    @Override
    public void initSessionFactories() {
        // Using Spring Data Mongo
    }

    public MongoProcessEngineConfiguration setProcessSchemaManager(
            MongoProcessSchemaManager processSchemaManager) {
        this.processSchemaManager = processSchemaManager;
        return this;
    }
}
