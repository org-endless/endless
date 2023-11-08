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
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.flowable.common.engine.impl.Page;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.job.api.Job;
import org.flowable.job.service.JobServiceConfiguration;
import org.flowable.job.service.impl.TimerJobQueryImpl;
import org.flowable.job.service.impl.persistence.entity.TimerJobEntity;
import org.flowable.job.service.impl.persistence.entity.TimerJobEntityImpl;
import org.flowable.job.service.impl.persistence.entity.data.TimerJobDataManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Joram Barrez
 */
public class MongoDbTimerJobDataManager extends AbstractMongoDbDataManager<TimerJobEntity>
        implements TimerJobDataManager {

    public static final String COLLECTION_TIMER_JOBS = "timerJobs";

    protected JobServiceConfiguration jobServiceConfiguration;

    public MongoDbTimerJobDataManager(JobServiceConfiguration jobServiceConfiguration) {
        super(mongoOperations);
        this.jobServiceConfiguration = jobServiceConfiguration;
    }

    @Override
    public String getCollection() {
        return COLLECTION_TIMER_JOBS;
    }

    @Override
    public TimerJobEntity create() {
        return new TimerJobEntityImpl();
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        TimerJobEntity jobEntity = (TimerJobEntity) entity;
        BasicDBObject updateObject = null;
        updateObject = setUpdateProperty(jobEntity, "retries", jobEntity.getRetries(), updateObject);
        updateObject =
                setUpdateProperty(
                        jobEntity, "exceptionMessage", jobEntity.getExceptionMessage(), updateObject);
        updateObject =
                setUpdateProperty(jobEntity, "lockOwner", jobEntity.getLockOwner(), updateObject);
        updateObject =
                setUpdateProperty(
                        jobEntity, "lockExpirationTime", jobEntity.getLockExpirationTime(), updateObject);
        updateObject = setUpdateProperty(jobEntity, "dueDate", jobEntity.getDuedate(), updateObject);
        return updateObject;
    }

    @Override
    public TimerJobEntity findJobByCorrelationId(String correlationId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionId(
            String jobHandlerType, String processDefinitionId) {
        return Collections.emptyList();
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionKeyNoTenantId(
            String jobHandlerType, String processDefinitionKey) {
        return Collections.emptyList();
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionKeyAndTenantId(
            String jobHandlerType, String processDefinitionKey, String tenantId) {
        return Collections.emptyList();
    }

    @Override
    public List<TimerJobEntity> findJobsByScopeIdAndSubScopeId(String scopeId, String subScopeId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TimerJobEntity> findJobsToExecute(List<String> enabledCategories, Page page) {
        Bson filter = null;
        List<Bson> filterParts = new ArrayList<>();
        if (jobServiceConfiguration.getJobExecutionScope() == null) {
            filterParts.add(Filters.eq("scopeType", null));

        } else if (!jobServiceConfiguration.getJobExecutionScope().equals("all")) {
            filterParts.add(Filters.eq("scopeType", jobServiceConfiguration.getJobExecutionScope()));
        }

        filterParts.add(Filters.lte("duedate", jobServiceConfiguration.getClock().getCurrentTime()));
        filterParts.add(Filters.eq("lockOwner", null));

        filter = Filters.and(filterParts);

        return getMongoDbSession().find(COLLECTION_TIMER_JOBS, filter, null, 1);
    }

    @Override
    public List<TimerJobEntity> findJobsByExecutionId(String executionId) {
        Bson filter = Filters.eq("executionId", executionId);
        return getMongoDbSession().find(COLLECTION_TIMER_JOBS, filter);
    }

    @Override
    public List<TimerJobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        Bson filter = Filters.eq("processInstanceId", processInstanceId);
        return getMongoDbSession().find(COLLECTION_TIMER_JOBS, filter);
    }

    @Override
    public List<TimerJobEntity> findExpiredJobs(List<String> enabledCategories, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Job> findJobsByQueryCriteria(TimerJobQueryImpl timerJobQuery) {
        List<Job> jobs = getMongoDbSession().find(COLLECTION_TIMER_JOBS, createFilter(timerJobQuery));
        return jobs;
    }

    @Override
    public long findJobCountByQueryCriteria(TimerJobQueryImpl timerJobQuery) {
        long count = getMongoDbSession().count(COLLECTION_TIMER_JOBS, createFilter(timerJobQuery));
        return count;
    }

    @Override
    public void bulkDeleteWithoutRevision(List<TimerJobEntity> timerJobEntities) {

    }

    protected Bson createFilter(TimerJobQueryImpl query) {
        List<Bson> filters = new ArrayList<>();
        if (query.getId() != null) {
            filters.add(Filters.eq("_id", query.getId()));
        }
        if (query.getExecutionId() != null) {
            filters.add(Filters.eq("executionId", query.getExecutionId()));
        }
        if (query.getProcessInstanceId() != null) {
            filters.add(Filters.eq("processInstanceId", query.getProcessInstanceId()));
        }
        if (query.getHandlerType() != null) {
            filters.add(Filters.eq("jobHandlerType", query.getHandlerType()));
        }
        if (query.getProcessDefinitionId() != null) {
            filters.add(Filters.eq("processDefinitionId", query.getProcessDefinitionId()));
        }
        if (query.getScopeId() != null) {
            filters.add(Filters.eq("scopeId", query.getScopeId()));
        }
        if (query.getSubScopeId() != null) {
            filters.add(Filters.eq("subScopeId", query.getSubScopeId()));
        }
        if (query.getScopeType() != null) {
            filters.add(Filters.eq("scopeType", query.getSubScopeId()));
        }
        if (query.getScopeDefinitionId() != null) {
            filters.add(Filters.eq("scopeDefinitionId", query.getScopeDefinitionId()));
        }
        if (query.getExecutable()) {
            filters.add(Filters.lt("duedate", jobServiceConfiguration.getClock().getCurrentTime()));
        }
        if (query.getDuedateHigherThan() != null) {
            filters.add(Filters.gt("duedate", query.getDuedateHigherThan()));
        }
        if (query.getDuedateLowerThan() != null) {
            filters.add(Filters.gt("duedate", query.getDuedateLowerThan()));
        }
        if (query.getDuedateHigherThanOrEqual() != null) {
            filters.add(Filters.gt("duedate", query.getDuedateHigherThanOrEqual()));
        }
        if (query.getDuedateLowerThanOrEqual() != null) {
            filters.add(Filters.gt("duedate", query.getDuedateLowerThanOrEqual()));
        }
        if (query.isWithException()) {
            throw new UnsupportedOperationException();
        }
        if (query.getExceptionMessage() != null) {
            filters.add(Filters.eq("exceptionMessage", query.getExceptionMessage()));
        }
        if (query.getTenantId() != null) {
            filters.add(Filters.eq("tenantId", query.getTenantId()));
        }
        if (query.getTenantIdLike() != null) {
            filters.add(Filters.regex("tenantId", query.getTenantIdLike().replace("%", ".*")));
        }
        if (query.isWithoutTenantId()) {
            filters.add(
                    Filters.or(
                            Filters.eq("tenantId", ProcessEngineConfiguration.NO_TENANT_ID),
                            Filters.not(Filters.exists("tenantId"))));
        }

        return makeAndFilter(filters);
    }

    protected String tenantId;
    protected String tenantIdLike;
    protected boolean withoutTenantId;

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bulkUpdateJobLockWithoutRevisionCheck(List<TimerJobEntity> jobEntities, String lockOwner, Date lockExpirationTime) {

    }

    @Override
    public void resetExpiredJob(String jobId) {
        throw new UnsupportedOperationException();
    }
}
