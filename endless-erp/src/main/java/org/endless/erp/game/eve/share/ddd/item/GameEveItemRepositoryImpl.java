package org.endless.erp.game.eve.share.ddd.item;

import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;


/**
 * @author Deng Haozhi
 * @date 2023/5/23 18:41
 * @since 0.0.3
 */
public class GameEveItemRepositoryImpl implements GameEveItemRepository{

    private final MongoTemplate mongoTemplate;

    public GameEveItemRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // public Slice<GameEveItem> findSliceByPublished(Boolean published) {
    //     var query = Query.query()
    //     mongoTemplate.find()
    // }
}