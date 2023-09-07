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
package mongo.config;

import mongo.persistence.manager.MongoDbHistoricTaskInstanceDataManager;
import mongo.persistence.manager.MongoDbTaskDataManager;
import org.flowable.task.service.TaskServiceConfiguration;

/**
 * @author Joram Barrez
 */
public class MongoDbTaskServiceConfiguration extends TaskServiceConfiguration {

    public MongoDbTaskServiceConfiguration(String engineName) {
        super(engineName);
    }

    @Override
    public void initDataManagers() {
        this.taskDataManager = new MongoDbTaskDataManager();

        this.historicTaskInstanceDataManager = new MongoDbHistoricTaskInstanceDataManager();
    }
}
