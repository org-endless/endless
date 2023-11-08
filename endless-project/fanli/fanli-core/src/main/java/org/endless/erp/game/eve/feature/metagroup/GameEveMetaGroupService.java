package org.endless.erp.game.eve.feature.metagroup;

import org.springframework.stereotype.Service;

/**
 * GameEveMetaGroupService
 *
 * <p>update 2023/4/20 18:28
 * <p>update 2023/8/15 21:04
 *
 * @author Deng Haozhi
 * @since 0.0.2
 */
@Service
public class GameEveMetaGroupService {
    // private final MongoBulkOperations bulkMongoRepository;
    //
    // public GameEveMetaGroupService(MongoBulkOperations bulkMongoRepository) {
    //     this.bulkMongoRepository = bulkMongoRepository;
    // }
    //
    // public void load(String file) {
    //
    //     var scanner = FileLoader.getScanner(file, ErpPattern.getEve());
    //     List<Pair<Query, Update>> upsertList = new ArrayList<>();
    //
    //     while (scanner.hasNext()) {
    //
    //         var metaGroupId = scanner.findInLine(ErpPattern.getEve()).replace(":", "").trim();
    //         var rat = (Map<?, ?>) ObjectToMongoObject.convert(new Yaml().load(scanner.next()));
    //
    //         var query = Query.query(Criteria.where("metaGroupId").is(metaGroupId));
    //         var update = Update.update("name.enName.fullName", ((Map<?, ?>) rat.get("nameID")).get("en"))
    //                 .set("name.zhName.fullName", ((Map<?, ?>) rat.get("nameID")).get("zh"))
    //                 .set("updateDateTime", DateFormatter.nowIso())
    //                 .set("updateTimeStamp", System.currentTimeMillis());
    //
    //         upsertList.add(Pair.of(query, update));
    //     }
    //     bulkMongoRepository.upsert(upsertList, PAGE_SIZE, org.endless.demo.metagroup.feature.eve.GameEveMetaGroup.class);
    //     System.out.println("GameEveMetaGroupService load executed!");
    // }

}
