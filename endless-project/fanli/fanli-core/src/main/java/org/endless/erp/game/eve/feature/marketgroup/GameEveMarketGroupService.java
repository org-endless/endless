package org.endless.erp.game.eve.feature.marketgroup;

import org.springframework.stereotype.Service;

/**
 * GameEveMarketGroupService
 *
 * <p>create 2023/4/20 17:59
 * <p>update 2023/8/15 21:03
 *
 * @author Deng Haozhi
 * @since 0.0.2
 */
@Service
public class GameEveMarketGroupService {

    // private final MongoBulkOperations bulkMongoRepository;
    //
    // public GameEveMarketGroupService(MongoBulkOperations bulkMongoRepository) {
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
    //     bulkMongoRepository.upsert(upsertList, PAGE_SIZE, GameEveMarketGroup.class);
    //     System.out.println("GameEveMarketGroupService Load executed!");
    // }
}
