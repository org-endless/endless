package document.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.engine.impl.persistence.entity.ResourceEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MongoResource
 * <p>
 * <p>
 * <p>create 2023/8/19 19:15
 * <p>update 2023/8/19 19:15
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_GE_BYTEARRAY")
public class MongoResource extends ResourceEntityImpl {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "REV_")
    private Integer revision;

    @Field(name = "NAME_")
    private String name;

    @Field(name = "DEPLOYMENT_ID_")
    private String deploymentId;

    @Field(name = "BYTES_")
    private String bytes;

    @Field(name = "GENERATED_")
    private Boolean generated;
}
