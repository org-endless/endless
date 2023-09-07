package mongo.document.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Model
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/19 8:35
 * <p>update 2023/8/19 8:35
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RE_MODEL")
public class Model {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "REV_")
    private Integer revision;

    @Field(name = "NAME_")
    private String name;

    @Field(name = "KEY_")
    private String key;

    @Field(name = "CATEGORY_")
    private String category;

    @Field(name = "CREATE_TIME_")
    private Date createTime;

    @Field(name = "LAST_UPDATE_TIME_")
    private Date lastUpdateTime;

    @Field(name = "VERSION_")
    private Integer version;

    @Field(name = "META_INFO_")
    private String metaInfo;

    @Indexed(name = "ACT_IDX_MODEL_DEPLOYMENT", background = true)
    @Field(name = "DEPLOYMENT_ID_")
    private String deploymentId;

    @Indexed(name = "ACT_IDX_MODEL_SOURCE", background = true)
    @Field(name = "EDITOR_SOURCE_VALUE_ID_")
    private String editorSourceValueId;

    @Indexed(name = "ACT_IDX_MODEL_SOURCE_EXTRA", background = true)
    @Field(name = "EDITOR_SOURCE_EXTRA_VALUE_ID_")
    private String editorSourceExtraValueId;

    @Field(name = "TENANT_ID_")
    private String tenantId = "";
}
