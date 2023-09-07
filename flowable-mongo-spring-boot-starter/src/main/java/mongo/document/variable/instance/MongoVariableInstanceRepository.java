package mongo.document.variable.instance;

import org.endless.spring.boot.data.mongo.base.BaseMongoRepository;

import java.util.Collection;

/**
 * MongoVariableInstanceRepository
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/19 20:34
 * <p>update 2023/8/19 20:34
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoVariableInstanceRepository extends BaseMongoRepository<MongoVariableInstanceDocument, String> {

    void deleteAllByTaskId(String taskId);

    void deleteAllByExecutionId(String executionId);

    void deleteAllByScopeIdAndScopeType(String scopeId, String scopeTypes);

    void deleteAllByScopeIdAndScopeTypeIn(String scopeId, Collection<String> scopeTypes);

    void deleteAllBySubScopeIdAndScopeTypeIn(String subScopeId, Collection<String> scopeTypes);
}
