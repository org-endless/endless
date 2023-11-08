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
package document.task;

import lombok.extern.slf4j.Slf4j;
import org.flowable.task.service.TaskServiceConfiguration;
import org.flowable.task.service.impl.persistence.entity.data.impl.MyBatisHistoricTaskLogEntryDataManager;
import org.flowable.task.service.impl.persistence.entity.data.impl.MybatisHistoricTaskInstanceDataManager;
import org.flowable.task.service.impl.persistence.entity.data.impl.MybatisTaskDataManager;

/**
 * MongoTaskServiceConfiguration
 * <p>
 * <p>create 2023/9/26 15:48
 * <p>update 2023/9/26 15:48
 *
 * @author Deng Haozhi
 * @see TaskServiceConfiguration
 * @since 0.0.4
 */
@Slf4j
public class MongoTaskServiceConfiguration extends TaskServiceConfiguration {

    public MongoTaskServiceConfiguration(String engineName) {
        super(engineName);
    }

    @Override
    public void initDataManagers() {

        if (taskDataManager == null) {
            log.debug("TaskDataManager Initialization.");
            taskDataManager = new MybatisTaskDataManager(this);
        }
        if (historicTaskInstanceDataManager == null) {
            log.debug("HistoricTaskInstanceDataManager Initialization.");
            historicTaskInstanceDataManager = new MybatisHistoricTaskInstanceDataManager(this);
        }
        if (historicTaskLogDataManager == null) {
            log.debug("HistoricTaskLogDataManager Initialization.");
            historicTaskLogDataManager = new MyBatisHistoricTaskLogEntryDataManager(this);
        }
    }

}
