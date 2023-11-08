/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package config;

import org.flowable.job.service.JobServiceConfiguration;
import persistence.manager.*;

/**
 * @author Joram Barrez
 */
public class MongoDbJobServiceConfiguration extends JobServiceConfiguration {

    public MongoDbJobServiceConfiguration(String engineName) {
        super(engineName);
    }

    @Override
    public void initDataManagers() {
        // TODO: other data managers
        if (jobDataManager == null) {
            this.jobDataManager = new MongoDbJobDataManager(this);
        }
        if (timerJobDataManager == null) {
            this.timerJobDataManager = new MongoDbTimerJobDataManager(this);
        }
        if (suspendedJobDataManager == null) {
            this.suspendedJobDataManager = new MongoDbSuspendedJobDataManager();
        }
        if (deadLetterJobDataManager == null) {
            this.deadLetterJobDataManager = new MongoDbDeadLetterJobDataManager();
        }
        if (externalWorkerJobDataManager == null) {
            this.externalWorkerJobDataManager = new MongoDbExternalWorkerJobDataManager();
        }

        // TODO: history
    }
}
