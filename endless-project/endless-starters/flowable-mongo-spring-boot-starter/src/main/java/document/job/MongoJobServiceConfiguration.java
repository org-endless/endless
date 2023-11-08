package document.job;

import org.flowable.job.service.JobServiceConfiguration;

/**
 * MongoJobServiceConfiguration
 * <p>
 * <p>
 * <p>create 2023/9/18 12:09
 * <p>update 2023/9/18 12:09
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoJobServiceConfiguration extends JobServiceConfiguration {

    public MongoJobServiceConfiguration(String engineName) {
        super(engineName);
    }

    @Override
    public void initDataManagers() {

    }
}
