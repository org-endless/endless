package document.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.engine.impl.persistence.entity.AttachmentEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoAttachment
 * <p>
 * <p>
 * <p>create 2023/8/18 9:57
 * <p>update 2023/8/18 9:57
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_ATTACHMENT")
public class MongoAttachment extends AttachmentEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @Field("REV_")
    private Integer revision;

    @Field("USER_ID_")
    private String userId;

    @Field("NAME_")
    private String name;

    @Field("DESCRIPTION_")
    private String description;

    @Field("TYPE_")
    private String type;

    @Field("TASK_ID_")
    private String taskId;

    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Field("URL_")
    private String url;

    @Field("CONTENT_ID_")
    private String contentId;

    @Field("TIME_")
    private Date time;
}
