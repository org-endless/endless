package document.activity.instance;

import org.flowable.engine.impl.persistence.entity.ActivityInstanceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * MongoActivityInstanceRepository
 * <p>
 * <p>
 * <p>create 2023/9/22 16:19
 * <p>update 2023/9/22 16:19
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public interface MongoActivityInstanceRepository extends MongoRepository<MongoActivityInstance, String> {
    List<ActivityInstanceEntity> findByExecutionIdAndActivityIdAndEndTime(String executionId, String activityId, Date endTime);

    List<ActivityInstanceEntity> findByExecutionIdAndActivityId(String executionId, String activityId);

    ActivityInstanceEntity findByTaskId(String taskId);

    List<ActivityInstanceEntity> findByProcessInstanceId(String processInstanceId);
}
