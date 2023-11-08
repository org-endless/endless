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
package persistence.manager;

import com.mongodb.BasicDBObject;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.job.api.Job;
import org.flowable.job.service.impl.SuspendedJobQueryImpl;
import org.flowable.job.service.impl.persistence.entity.SuspendedJobEntity;
import org.flowable.job.service.impl.persistence.entity.data.SuspendedJobDataManager;

import java.util.Collections;
import java.util.List;

/**
 * @author Joram Barrez
 */
public class MongoDbSuspendedJobDataManager extends AbstractMongoDbDataManager<SuspendedJobEntity>
        implements SuspendedJobDataManager {

    public static final String COLLECTION_SUSPENDED_JOBS = "suspendedJobs";

    public MongoDbSuspendedJobDataManager() {
        super(mongoOperations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_SUSPENDED_JOBS;
    }

    @Override
    public SuspendedJobEntity create() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        return null;
    }

    @Override
    public SuspendedJobEntity findJobByCorrelationId(String correlationId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SuspendedJobEntity> findJobsByExecutionId(String executionId) {
        return Collections.emptyList();
    }

    @Override
    public List<SuspendedJobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Job> findJobsByQueryCriteria(SuspendedJobQueryImpl jobQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findJobCountByQueryCriteria(SuspendedJobQueryImpl jobQuery) {
        return 0;
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }
}
