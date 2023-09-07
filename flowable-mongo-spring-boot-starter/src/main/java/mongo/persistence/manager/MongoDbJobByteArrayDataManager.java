/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mongo.persistence.manager;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayEntity;
import org.flowable.common.engine.impl.persistence.entity.ByteArrayEntityImpl;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.common.engine.impl.persistence.entity.data.ByteArrayDataManager;

import java.util.List;

/**
 * @author Joram Barrez
 */
public class MongoDbJobByteArrayDataManager extends AbstractMongoDbDataManager<ByteArrayEntity>
        implements ByteArrayDataManager {

    public static final String COLLECTION_JOB_BYTE_ARRAY = "jobByteArrays";

    public MongoDbJobByteArrayDataManager() {
        super(mongoOperations);
    }

    @Override
    public String getCollection() {
        return COLLECTION_JOB_BYTE_ARRAY;
    }

    @Override
    public ByteArrayEntity create() {
        return new ByteArrayEntityImpl();
    }

    @Override
    public BasicDBObject createUpdateObject(Entity entity) {
        return null;
    }

    @Override
    public List<ByteArrayEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteByteArrayNoRevisionCheck(String byteArrayEntityId) {
        getMongoDbSession().bulkDelete(COLLECTION_JOB_BYTE_ARRAY, Filters.eq("_id", byteArrayEntityId));
    }
}
