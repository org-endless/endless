package document.process.definition;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MongoProcessDefinition
 * <p>
 * <p>
 * <p>create 2023/8/19 8:46
 * <p>update 2023/8/19 8:46
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_RE_PROCDEF")
@CompoundIndexes({
        @CompoundIndex(unique = true, name = "ACT_UNIQ_PROCDEF", def = "{'KEY_' : 1, 'VERSION_' : 1, 'DERIVED_VERSION_' : 1, 'TENANT_ID_' : 1}", background = true)})
public class MongoProcessDefinition extends ProcessDefinitionEntityImpl {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "REV_")
    private Integer revision;

    @Field(name = "CATEGORY_")
    private String category;

    @Field(name = "NAME_")
    private String name;

    @NonNull
    @Field(name = "KEY_")
    private String key;

    @NonNull
    @Field(name = "VERSION_")
    private Integer version;

    @Field(name = "DEPLOYMENT_ID_")
    private String deploymentId;

    @Field(name = "RESOURCE_NAME_")
    private String resourceName;

    @Field(name = "DGRM_RESOURCE_NAME_")
    private String diagramResourceName;

    @Field(name = "DESCRIPTION_")
    private String description;

    @Field(name = "HAS_START_FORM_KEY_")
    private Boolean hasStartFormKey;

    @Field(name = "HAS_GRAPHICAL_NOTATION_")
    private Boolean isGraphicalNotationDefined;

    @Field(name = "DESCRIPTION_")
    private Integer suspensionState;

    @Field(name = "TENANT_ID_")
    private String tenantId = "";

    @Field(name = "DERIVED_FROM_")
    private String derivedFrom;

    @Field(name = "DERIVED_FROM_ROOT_")
    private String derivedFromRoot;

    @NonNull
    @Field(name = "DERIVED_VERSION_")
    private Integer derivedVersion = 0;

    @Field(name = "ENGINE_VERSION_")
    private String engineVersion;
}
