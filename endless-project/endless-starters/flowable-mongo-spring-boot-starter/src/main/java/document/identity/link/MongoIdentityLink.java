package document.identity.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.identitylink.service.impl.persistence.entity.IdentityLinkEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * MongoIdentityLink
 * <p>
 * <p>
 * <p>create 2023/8/19 7:33
 * <p>update 2023/8/19 7:33
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_IDENTITYLINK")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_HI_IDENT_LNK_SCOPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_IDENT_LNK_SUB_SCOPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_IDENT_LNK_SCOPE_DEF", def = "{'SCOPE_DEFINITION_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoIdentityLink extends IdentityLinkEntityImpl {

    @Id
    @Field(name = "ID_")
    private String id;

    @Field(name = "GROUP_ID_")
    private String groupId;

    @Field(name = "TYPE_")
    private String type;

    @Indexed(name = "ACT_IDX_HI_IDENT_LNK_USER", background = true)
    @Field(name = "USER_ID_")
    private String userId;

    @Field(name = "TASK_ID_")
    private String taskId;

    @Field(name = "CREATE_TIME_")
    private Date createTime;

    @Field(name = "PROC_INST_ID_")
    private String processInstanceId;

    @Field(name = "SCOPE_ID_")
    private String scopeId;

    @Field(name = "SUB_SCOPE_ID_")
    private String subScopeId;

    @Field(name = "SCOPE_TYPE_")
    private String scopeType;

    @Field(name = "SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;
}
