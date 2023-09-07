package mongo.document.historic.identity.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * HistoricIdentityLink
 * <p>
 * <p>
 * <p>
 * <p>create 2023/8/19 3:36
 * <p>update 2023/8/19 3:36
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_IDENTITYLINK")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_HI_IDENT_LNK_SCOPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_IDENT_LNK_SUB_SCOPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_IDENT_LNK_SCOPE_DEF", def = "{'SCOPE_DEFINITION_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class HistoricIdentityLink {

    @Id
    @Field("ID_")
    private String id;

    @Field("GROUP_ID_")
    private String groupId;

    @Field("TYPE_")
    private String type;

    @Indexed(name = "ACT_IDX_HI_IDENT_LNK_USER", background = true)
    @Field("USER_ID_")
    private String userId;

    @Field("TASK_ID_")
    private String taskId;

    @Field("CREATE_TIME_")
    private Date createTime;

    @Field("PROC_INST_ID_")
    private String processInstanceId;

    @Field("SCOPE_ID_")
    private String scopeId;

    @Field("SUB_SCOPE_ID_")
    private String subScopeId;

    @Field("SCOPE_TYPE_")
    private String scopeType;

    @Field("SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;
}
