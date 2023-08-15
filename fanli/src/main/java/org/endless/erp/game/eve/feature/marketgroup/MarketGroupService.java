package org.endless.erp.game.eve.feature.marketgroup;

import org.springframework.stereotype.Service;

/**
 * MarketGroupService
 *
 * @author Deng Haozhi
 * @date 2023/4/20 17:59
 * @since 0.0.2
 */
@Service
public class MarketGroupService {

    // private final BulkMongoOperations bulkMongoRepository;
    //
    // public MarketGroupService(BulkMongoOperations bulkMongoRepository) {
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
    //         var marketGroupId = scanner.findInLine(ErpPattern.getEve()).replace(":", "").trim();
    //         var rat = (Map<?, ?>) ObjectToMongoObject.convert(new Yaml().load(scanner.next()));
    //
    //         var query = Query.query(Criteria.where("marketGroupId").is(marketGroupId));
    //         var update = Update.update("parentGroupId", rat.get("parentGroupID"))
    //                 .set("name.enName.fullName", ((Map<?, ?>) rat.get("nameID")).get("en"))
    //                 .set("name.zhName.fullName", ((Map<?, ?>) rat.get("nameID")).get("zh"))
    //                 .set("hasTypes", rat.get("hasTypes"))
    //                 .set("updateDateTime", DateFormatter.nowIso())
    //                 .set("updateTimeStamp", System.currentTimeMillis());
    //
    //         upsertList.add(Pair.of(query, update));
    //     }
    //     bulkMongoRepository.upsert(upsertList, PAGE_SIZE, MarketGroup.class);
    //     System.out.println("MarketGroupService Load executed!");
    // }
}
