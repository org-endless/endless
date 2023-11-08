package document.model;

import document.AbstractMongoDataManager;
import org.flowable.engine.impl.ModelQueryImpl;
import org.flowable.engine.impl.persistence.entity.ModelEntity;
import org.flowable.engine.impl.persistence.entity.data.ModelDataManager;
import org.flowable.engine.repository.Model;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;

/**
 * MongoModelDataManager
 * <p>
 * <p>
 * <p>create 2023/9/22 16:35
 * <p>update 2023/9/22 16:35
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoModelDataManager extends AbstractMongoDataManager<ModelEntity>
        implements ModelDataManager {

    public static final String COLLECTION_MODEL = "ACT_RE_MODEL";

    public MongoModelDataManager(MongoOperations operations) {
        super(operations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_MODEL;
    }

    @Override
    public Class<ModelEntity> getEntityClass() {
        return ModelEntity.class;
    }

    @Override
    public Update getUpdateObject(ModelEntity entity) {
        return null;
    }

    @Override
    public List<Model> findModelsByQueryCriteria(ModelQueryImpl query) {
        return null;
    }

    @Override
    public long findModelCountByQueryCriteria(ModelQueryImpl query) {
        return 0;
    }

    @Override
    public List<Model> findModelsByNativeQuery(Map<String, Object> parameterMap) {
        return null;
    }

    @Override
    public long findModelCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public ModelEntity create() {
        return null;
    }
}
