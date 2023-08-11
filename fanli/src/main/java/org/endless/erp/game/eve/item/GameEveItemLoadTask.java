package org.endless.erp.game.eve.item;

import lombok.extern.log4j.Log4j2;
import org.endless.com.utiliy.date.DateFormatter;
import org.endless.com.utiliy.decimal.Decimal;
import org.endless.com.utiliy.object.ObjectToMongoObject;
import org.endless.data.mongo.bulk.BulkMongoRepository;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.ddd.item.Item;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.endless.erp.share.ddd.industry.Industry.GAME_EVE;

/**
 * GameEveItemLoadTask
 * <p>游戏EVE物品/商品数据文件加载异步任务
 * <p>Game EVE item data file loading asynchronous task.
 * <p>
 * <p>create 2023/05/11 11:05
 * <p>update 2023/05/27 19:50
 *
 * @author Deng Haozhi
 * @see GameEveAsyncTask
 * @since 0.0.3
 */
@Log4j2
@Component("gameEveItemLoadTask")
public class GameEveItemLoadTask implements GameEveAsyncTask {

    private final BulkMongoRepository bulkRepository;

    public GameEveItemLoadTask(BulkMongoRepository bulkRepository) {
        this.bulkRepository = bulkRepository;
    }


    /**
     * 运行游戏EVE物品/商品数据文件加载异步任务
     * <p>Run the game EVE item data file loading asynchronous task.
     *
     * @param scannerMaps 文件扫描结果 (File scan results)
     */
    @Override
    public <T> void run(T scannerMaps) {

        log.debug("Thread: " + Thread.currentThread().getName() + " load executing");

        List<Pair<Query, Update>> pairs = new ArrayList<>();

        ((List<?>) scannerMaps).forEach(scannerMap -> {
            var scanner = String.valueOf(((Map<?, ?>) scannerMap).get("scanner"));
            var itemId = String.valueOf(((Map<?, ?>) scannerMap).get("itemId"));
            var rat = (Map<?, ?>) ObjectToMongoObject.convert(new Yaml().load(scanner));

            var id = Item.getId(GAME_EVE, itemId);
            var query = Query.query(Criteria.where("id").is(id));
            var update = Update
                    .update("itemId", itemId)
                    .set("industryId", GAME_EVE)
                    .set("name.enName.fullName", ((Map<?, ?>) rat.get("name")).get("en"))
                    .set("name.zhName.fullName", ((Map<?, ?>) rat.get("name")).get("zh"))
                    .set("price.basePrice", Decimal.format(rat.get("basePrice")))
                    .set("mass", Decimal.format(rat.get("mass")))
                    .set("volume", Decimal.format(rat.get("volume")))
                    .set("published", rat.get("published"))
                    .set("eveGroupId", rat.get("groupID"))
                    .set("eveMarketGroupId", rat.get("marketGroupID"))
                    .set("eveMetaGroupId", rat.get("metaGroupID"))
                    .set("updateDateTime", DateFormatter.nowIso())
                    .set("updateTimeStamp", System.currentTimeMillis());
            pairs.add(Pair.of(query, update));
        });

        log.trace(pairs);
        bulkRepository.upsert(pairs, GameEveItem.class);

        log.info("Thread: " + Thread.currentThread().getName() + " load executed completely!");
    }
}
