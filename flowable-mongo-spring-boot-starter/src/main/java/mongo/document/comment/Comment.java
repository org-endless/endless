package mongo.document.comment;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.engine.impl.persistence.entity.CommentEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Comment
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/18 23:48
 * <p>update 2023/8/18 23:48
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_COMMENT")
public class Comment extends CommentEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @Field("TYPE_")
    private String type;

    @NonNull
    @Field("TIME_")
    private Date time;

    @Field("USER_ID_")
    private String userId;

    @Field("TASK_ID_")
    private String taskId;

    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Field("ACTION_")
    private String action;

    @Field("MESSAGE_")
    private String message;

    @Field("FULL_MSG_")
    private String fullMessage;


}
