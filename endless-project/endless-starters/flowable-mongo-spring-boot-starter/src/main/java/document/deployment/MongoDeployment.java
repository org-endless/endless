package document.deployment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoDeployment
 * <p>
 * <p>
 * <p>create 2023/8/18 23:55
 * <p>update 2023/8/18 23:55
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RE_DEPLOYMENT")
public class MongoDeployment extends DeploymentEntityImpl {

    @Id
    @Field("ID_")
    private String id;

    @Field("NAME_")
    private String name;

    @Field("CATEGORY_")
    private String category;

    @Field("KEY_")
    private String key;

    @Field("TENANT_ID_")
    private String tenantId = "";

    @Field("DEPLOY_TIME_")
    private Date deploymentTime;

    @Field("DERIVED_FROM_")
    private String derivedFrom;

    @Field("DERIVED_FROM_ROOT_")
    private String derivedFromRoot;

    @Field("PARENT_DEPLOYMENT_ID_")
    private String parentDeploymentId;

    @Field("ENGINE_VERSION_")
    private String engineVersion;
}
