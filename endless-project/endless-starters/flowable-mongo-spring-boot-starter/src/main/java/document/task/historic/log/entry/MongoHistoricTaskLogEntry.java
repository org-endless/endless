package document.task.historic.log.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.flowable.task.service.impl.persistence.entity.HistoricTaskLogEntryEntityImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MongoHistoricTaskLogEntry
 * <p>
 * <p>
 * <p>create 2023/8/19 4:19
 * <p>update 2023/8/19 4:19
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("ACT_HI_TASKINST")
@CompoundIndexes({
        @CompoundIndex(name = "ACT_IDX_HI_TASK_SCOPE", def = "{'SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_TASK_SUB_SCOPE", def = "{'SUB_SCOPE_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true),
        @CompoundIndex(name = "ACT_IDX_HI_TASK_SCOPE_DEF", def = "{'SCOPE_DEFINITION_ID_' : 1, 'SCOPE_TYPE_' : 1}", background = true)})
public class MongoHistoricTaskLogEntry extends HistoricTaskLogEntryEntityImpl {

    @Id
    @Field("ID_")
    private String id;

}
