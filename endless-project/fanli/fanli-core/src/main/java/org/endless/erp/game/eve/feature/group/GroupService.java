package org.endless.erp.game.eve.feature.group;

import org.springframework.stereotype.Service;

/**
 * GroupService load groups from yaml data file
 *
 * @author Deng Haozhi
 * @date 2023/3/7 1:28
 * @since 0.0.2
 */
@Service
public class GroupService {
    // private final MongoBulkOperations bulkMongoRepository;
    //
    // public GroupService(MongoBulkOperations bulkMongoRepository) {
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
    //         var groupId = scanner.findInLine(ErpPattern.getEve()).replace(":", "").trim();
    //         var rat = (Map<?, ?>) ObjectToMongoObject.convert(new Yaml().load(scanner.next()));
    //         var query = Query.query(Criteria.where("groupId").is(groupId));
    //         var update = Update.update("categoryId", rat.get("categoryID"))
    //                 .set("name.enName.fullName", ((Map<?, ?>) rat.get("name")).get("en"))
    //                 .set("name.zhName.fullName", ((Map<?, ?>) rat.get("name")).get("zh"))
    //                 .set("published", rat.get("published"))
    //                 .set("updateDateTime", DateFormatter.nowIso())
    //                 .set("updateTimeStamp", System.currentTimeMillis());
    //
    //         upsertList.add(Pair.of(query, update));
    //     }
    //     bulkMongoRepository.upsert(upsertList, PAGE_SIZE, Group.class);
    //     System.out.println("GroupService executed!");
    // }
}
