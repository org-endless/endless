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

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import mongo.persistence.MongoDbSessionFactory;
import mongo.persistence.manager.*;
import mongo.schema.MongoProcessSchemaManager;
import mongo.transaction.MongoDbTransactionContextFactory;
import org.flowable.common.engine.api.scope.ScopeTypes;
import org.flowable.common.engine.impl.interceptor.CommandInterceptor;
import org.flowable.common.engine.impl.persistence.GenericManagerFactory;
import org.flowable.common.engine.impl.persistence.StrongUuidGenerator;
import org.flowable.common.engine.impl.persistence.cache.EntityCache;
import org.flowable.common.engine.impl.persistence.cache.EntityCacheImpl;
import org.flowable.engine.impl.SchemaOperationsProcessEngineBuild;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.eventsubscription.service.EventSubscriptionServiceConfiguration;
import org.flowable.identitylink.service.IdentityLinkServiceConfiguration;
import org.flowable.job.service.JobServiceConfiguration;
import org.flowable.task.service.TaskServiceConfiguration;
import org.flowable.variable.service.VariableServiceConfiguration;

import java.util.ArrayList;

/**
 * @author Joram Barrez
 */
public class MongoDbProcessEngineConfiguration extends ProcessEngineConfigurationImpl {

    protected MongoClient mongoClient;
    @lombok.Getter
    protected MongoDatabase mongoDatabase;
    @lombok.Getter
    protected MongoProcessSchemaManager processSchemaManager;
    protected MongoDbSessionFactory mongoDbSessionFactory;

    public MongoDbProcessEngineConfiguration(MongoClient mongoClient, MongoDatabase mongoDatabase) {
        this.mongoClient = mongoClient;
        this.mongoDatabase = mongoDatabase;
        this.usingRelationalDatabase = false;
        this.usingSchemaMgmt = true;
        this.databaseSchemaUpdate = DB_SCHEMA_UPDATE_TRUE;

        // No backwards compatibility needed
        this.validateFlowable5EntitiesEnabled = false;

        // Always enabled for mongo db, so no need to validate
        this.performanceSettings.setValidateExecutionRelationshipCountConfigOnBoot(false);
        this.performanceSettings.setValidateTaskRelationshipCountConfigOnBoot(false);

        this.performanceSettings.setEnableEagerExecutionTreeFetching(true);
        this.performanceSettings.setEnableExecutionRelationshipCounts(true);
        this.performanceSettings.setEnableTaskRelationshipCounts(true);

        this.idGenerator = new StrongUuidGenerator();

        this.disableEventRegistry = true;
    }

    @Override
    public void init() {
        super.init();

        mongoDbSessionFactory.registerDataManager(
                MongoDbEventSubscriptionDataManager.COLLECTION_EVENT_SUBSCRIPTION,
                (AbstractMongoDbDataManager)
                        eventSubscriptionServiceConfiguration.getEventSubscriptionDataManager());

        mongoDbSessionFactory.registerDataManager(
                MongoDbIdentityLinkDataManager.COLLECTION_IDENTITY_LINKS,
                (AbstractMongoDbDataManager) identityLinkServiceConfiguration.getIdentityLinkDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbHistoricIdentityLinkDataManager.COLLECTION_HISTORIC_IDENTITY_LINKS,
                (AbstractMongoDbDataManager)
                        identityLinkServiceConfiguration.getHistoricIdentityLinkDataManager());

        mongoDbSessionFactory.registerDataManager(
                MongoDbJobDataManager.COLLECTION_JOBS,
                (AbstractMongoDbDataManager) jobServiceConfiguration.getJobDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbTimerJobDataManager.COLLECTION_TIMER_JOBS,
                (AbstractMongoDbDataManager) jobServiceConfiguration.getTimerJobDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbSuspendedJobDataManager.COLLECTION_SUSPENDED_JOBS,
                (AbstractMongoDbDataManager) jobServiceConfiguration.getSuspendedJobDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbDeadLetterJobDataManager.COLLECTION_DEADLETTER_JOBS,
                (AbstractMongoDbDataManager) jobServiceConfiguration.getDeadLetterJobDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbExternalWorkerJobDataManager.COLLECTION_EXTERNAL_WORKER_JOBS,
                (AbstractMongoDbDataManager) jobServiceConfiguration.getExternalWorkerJobDataManager());

        mongoDbSessionFactory.registerDataManager(
                MongoDbDeploymentDataManager.COLLECTION_DEPLOYMENT,
                (AbstractMongoDbDataManager) getDeploymentDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbResourceDataManager.COLLECTION_BYTE_ARRAY,
                (AbstractMongoDbDataManager) getResourceDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbProcessDefinitionDataManager.COLLECTION_PROCESS_DEFINITIONS,
                (AbstractMongoDbDataManager) getProcessDefinitionDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbExecutionDataManager.COLLECTION_EXECUTIONS,
                (AbstractMongoDbDataManager) getExecutionDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbProcessDefinitionInfoDataManager.COLLECTION_PROCESS_DEFINITION_INFO,
                (AbstractMongoDbDataManager) getProcessDefinitionInfoDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbCommentDataManager.COLLECTION_COMMENTS,
                (AbstractMongoDbDataManager) getCommentDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbModelDataManager.COLLECTION_MODELS,
                (AbstractMongoDbDataManager) getModelDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbHistoricProcessInstanceDataManager.COLLECTION_HISTORIC_PROCESS_INSTANCES,
                (AbstractMongoDbDataManager) getHistoricProcessInstanceDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbActivityInstanceDataManager.COLLECTION_ACTIVITY_INSTANCES,
                (AbstractMongoDbDataManager) getActivityInstanceDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbHistoricActivityInstanceDataManager.COLLECTION_HISTORIC_ACTIVITY_INSTANCES,
                (AbstractMongoDbDataManager) getHistoricActivityInstanceDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbHistoricDetailDataManager.COLLECTION_HISTORIC_DETAILS,
                (AbstractMongoDbDataManager) getHistoricDetailDataManager());

        mongoDbSessionFactory.registerDataManager(
                MongoDbTaskDataManager.COLLECTION_TASKS,
                (AbstractMongoDbDataManager) taskServiceConfiguration.getTaskDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbHistoricTaskInstanceDataManager.COLLECTION_HISTORIC_TASK_INSTANCES,
                (AbstractMongoDbDataManager) taskServiceConfiguration.getHistoricTaskInstanceDataManager());

        mongoDbSessionFactory.registerDataManager(
                MongoDbVariableInstanceDataManager.COLLECTION_VARIABLES,
                (AbstractMongoDbDataManager) variableServiceConfiguration.getVariableInstanceDataManager());
        mongoDbSessionFactory.registerDataManager(
                MongoDbHistoricVariableInstanceDataManager.COLLECTION_HISTORIC_VARIABLE_INSTANCES,
                (AbstractMongoDbDataManager)
                        variableServiceConfiguration.getHistoricVariableInstanceDataManager());
    }

    @Override
    public void initSchemaManager() {
        this.schemaManager = new MongoProcessSchemaManager();
    }

    public void initSchemaManagementCommand() {
        // Impl note: the schemaMgmtCmd of the regular impl is reused, as it will delegate to the
        // MongoProcessSchemaManager class
        if (schemaManagementCmd == null) {
            this.schemaManagementCmd = new SchemaOperationsProcessEngineBuild();
        }
    }

    @Override
    public CommandInterceptor createTransactionInterceptor() {
        return null;
        //        return new MongoDbTransactionInterceptor(mongoClient); --> commented out, transaction
        // is started in MongoDbSession
    }

    @Override
    public void initTransactionContextFactory() {
        if (transactionContextFactory == null) {
            transactionContextFactory = new MongoDbTransactionContextFactory();
        }
    }

    @Override
    public void initDataManagers() {
        this.deploymentDataManager = new MongoDbDeploymentDataManager(this);

        this.resourceDataManager = new MongoDbResourceDataManager(this);

        this.processDefinitionDataManager = new MongoDbProcessDefinitionDataManager(this);

        this.executionDataManager = new MongoDbExecutionDataManager(this);

        this.processDefinitionInfoDataManager = new MongoDbProcessDefinitionInfoDataManager(this);

        this.commentDataManager = new MongoDbCommentDataManager(this);

        this.modelDataManager = new MongoDbModelDataManager(this);

        this.historicProcessInstanceDataManager = new MongoDbHistoricProcessInstanceDataManager(this);

        this.activityInstanceDataManager = new MongoDbActivityInstanceDataManager(this);

        this.historicActivityInstanceDataManager = new MongoDbHistoricActivityInstanceDataManager(this);

        this.historicDetailDataManager = new MongoDbHistoricDetailDataManager(this);
    }

    @Override
    protected JobServiceConfiguration instantiateJobServiceConfiguration() {
        return new MongoDbJobServiceConfiguration(ScopeTypes.BPMN);
    }

    @Override
    protected TaskServiceConfiguration instantiateTaskServiceConfiguration() {
        return new MongoDbTaskServiceConfiguration(ScopeTypes.BPMN);
    }

    @Override
    protected IdentityLinkServiceConfiguration instantiateIdentityLinkServiceConfiguration() {
        return new MongoDbIdentityLinkServiceConfiguration(ScopeTypes.BPMN);
    }

    @Override
    protected VariableServiceConfiguration instantiateVariableServiceConfiguration() {
        return new MongoDbVariableServiceConfiguration(ScopeTypes.BPMN);
    }

    @Override
    protected EventSubscriptionServiceConfiguration
    instantiateEventSubscriptionServiceConfiguration() {
        return new MongoDbEventSubscriptionServiceConfiguration(ScopeTypes.BPMN);
    }

    @Override
    public void initSessionFactories() {

        if (this.customSessionFactories == null) {
            this.customSessionFactories = new ArrayList<>();
        }

        this.customSessionFactories.add(
                new GenericManagerFactory(EntityCache.class, EntityCacheImpl.class));

        initMongoDbSessionFactory();
        this.customSessionFactories.add(mongoDbSessionFactory);

        super.initSessionFactories();
    }

    public void initMongoDbSessionFactory() {
        if (this.mongoDbSessionFactory == null) {
            this.mongoDbSessionFactory = new MongoDbSessionFactory(mongoClient, mongoDatabase);
        }
    }

    public MongoDbProcessEngineConfiguration setProcessSchemaManager(
            MongoProcessSchemaManager processSchemaManager) {
        this.processSchemaManager = processSchemaManager;
        return this;
    }
}
