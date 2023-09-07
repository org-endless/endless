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
package mongo.persistence.mapper;

import org.bson.Document;
import org.flowable.engine.impl.persistence.entity.DeploymentEntityImpl;

public class DeploymentEntityMapper extends AbstractEntityToDocumentMapper<DeploymentEntityImpl> {

    @Override
    public DeploymentEntityImpl fromDocument(Document document) {
        DeploymentEntityImpl deploymentEntity = new DeploymentEntityImpl();
        deploymentEntity.setId(document.getString("_id"));
        deploymentEntity.setName(document.getString("name"));
        deploymentEntity.setKey(document.getString("key"));
        deploymentEntity.setParentDeploymentId(document.getString("parentDeployment"));
        deploymentEntity.setDeploymentTime(document.getDate("deploymentTime"));
        deploymentEntity.setDerivedFrom(document.getString("derivedFrom"));
        deploymentEntity.setDerivedFromRoot(document.getString("derivedFromRoot"));
        deploymentEntity.setTenantId(document.getString("tenantId"));
        return deploymentEntity;
    }

    @Override
    public Document toDocument(DeploymentEntityImpl deploymentEntity) {
        // Note: no revision entity
        Document deploymentDocument = new Document();
        appendIfNotNull(deploymentDocument, "_id", deploymentEntity.getId());
        appendIfNotNull(deploymentDocument, "name", deploymentEntity.getName());
        appendIfNotNull(deploymentDocument, "key", deploymentEntity.getName());
        appendIfNotNull(
                deploymentDocument, "parentDeploymentId", deploymentEntity.getParentDeploymentId());

        appendIfNotNull(deploymentDocument, "deploymentTime", deploymentEntity.getDeploymentTime());
        appendIfNotNull(deploymentDocument, "derivedFrom", deploymentEntity.getDerivedFrom());
        appendIfNotNull(deploymentDocument, "derivedFromRoot", deploymentEntity.getDerivedFromRoot());

        appendIfNotNull(deploymentDocument, "tenantId", deploymentEntity.getTenantId());
        return deploymentDocument;
    }
}
