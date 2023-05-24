package org.endless.erp.game.eve.share.ddd.item;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.mongo.bulk.BulkMongoRepository;
import org.endless.erp.share.util.date.DateFormatter;
import org.endless.erp.share.util.decimal.Decimal;
import org.endless.erp.share.util.object.ObjectToMongoObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GameEveItemLoadTask
 *
 * @author Deng Haozhi
 * @date 2023/5/11 11:05
 * @since 0.0.2
 */
@Log4j2
@Component("gameEveItemLoadTask")
public class GameEveItemLoadTask implements GameEveAsyncTask {

    private final BulkMongoRepository bulkMongoRepository;

    public GameEveItemLoadTask(BulkMongoRepository bulkMongoRepository) {
        this.bulkMongoRepository = bulkMongoRepository;
    }

    @Override
    public <T> void run(T scannerMaps) {

        long begin = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " loading begin: " + begin);

        List<Pair<Query, Update>> pairs = new ArrayList<>();
        var industryId = "game.eve";

        ((List<?>) scannerMaps).forEach(scannerMap -> {
            var scanner = String.valueOf(((Map<?, ?>) scannerMap).get("scanner"));
            var itemId = String.valueOf(((Map<?, ?>) scannerMap).get("itemId"));
            var rat = (Map<?, ?>) ObjectToMongoObject.convert(new Yaml().load(scanner));

            var id = industryId + "_" + itemId;
            var query = Query.query(Criteria.where("id").is(id));
            var update = Update
                    .update("itemId", itemId)
                    .set("industryId", industryId)
                    .set("name.enName.fullName", ((Map<?, ?>) rat.get("name")).get("en"))
                    .set("name.zhName.fullName", ((Map<?, ?>) rat.get("name")).get("zh"))
                    .set("price.basePrice", Decimal.format(rat.get("basePrice")))
                    .set("mass", rat.get("mass"))
                    .set("volume", rat.get("volume"))
                    .set("published", rat.get("published"))
                    .set("groupId", rat.get("groupID"))
                    .set("marketGroupId", rat.get("marketGroupID"))
                    .set("metaGroupId", rat.get("metaGroupID"))
                    .set("updateDateTime", DateFormatter.nowIso())
                    .set("updateTimeStamp", System.currentTimeMillis());
            pairs.add(Pair.of(query, update));
        });

        log.trace(pairs);
        bulkMongoRepository.upsert(pairs, GameEveItem.class);

        long end = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " loading end: " + end);
        log.info("Thread: " + Thread.currentThread().getName() + " loaded completed!");
    }
}