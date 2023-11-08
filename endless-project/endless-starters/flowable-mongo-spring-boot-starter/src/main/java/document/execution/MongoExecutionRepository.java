package document.execution;

import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * MongoExecutionRepository
 * <p>
 * <p>
 * <p>create 2023/9/21 11:00
 * <p>update 2023/9/21 11:00
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoExecutionRepository extends MongoRepository<MongoExecution, String> {

    ExecutionEntity findBySuperExecutionId(String superExecutionId);

    List<ExecutionEntity> findByParentId(String parentId);

    List<ExecutionEntity> findByProcessInstanceIdAndParentIdIsNotNull(String processInstanceId);

    List<ExecutionEntity> findByParentIdAndActivityIdIn(String parentExecutionId, Collection<String> activityIds);

    List<ExecutionEntity> findByRootProcessInstanceId(String rootProcessInstanceId);

    List<ExecutionEntity> findByProcessInstanceId(String processInstanceId);

    List<ExecutionEntity> findByProcessInstanceIdAndIsActive(String processInstanceId, Boolean isActive);

    List<ExecutionEntity> findByActivityIdAndProcessInstanceIdAndIsActive(String activityId, String processInstanceId, Boolean isActive);

    @Query(fields = "{'id': 1}")
    List<String> findByProcessDefinitionIdAndParentIdIsNull(String processDefinitionId);

    long countByParentIdAndIsActive(String parentId, Boolean isActive);

}
