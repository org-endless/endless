package org.endless.erp.game.eve.feature.category;

import org.springframework.stereotype.Service;

/**
 * CategoryService
 *
 * @author Deng Haozhi
 * @date 2023/4/20 17:37
 * @since 0.0.2
 */
@Service
public class CategoryService {

  // private final BulkMongoRepository bulkMongoRepository;
  //
  // public CategoryService(BulkMongoRepository bulkMongoRepository) {
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
  //         var categoryId = scanner.findInLine(BasePattern.getEve()).replace(":", "").trim();
  //         var rat = (Map<?, ?>) ObjectToMongoObject.convert(new Yaml().load(scanner.next()));
  //
  //         var query = Query.query(Criteria.where("categoryId").is(categoryId));
  //         var update = Update.update("name.enName.fullName", ((Map<?, ?>)
  // rat.get("name")).get("en"))
  //                 .set("name.zhName.fullName", ((Map<?, ?>) rat.get("name")).get("zh"))
  //
  //                 .set("published", rat.get("published"))
  //                 .set("updateDateTime", DateFormatter.nowIso())
  //                 .set("updateTimeStamp", System.currentTimeMillis());
  //
  //         upsertList.add(Pair.of(query, update));
  //     }
  //     bulkMongoRepository.upsert(upsertList, PAGE_SIZE, Category.class);
  //     System.out.println("CategoryService executed!");
  // }
}
