package org.endless.erp.game.eve.item;

import org.endless.erp.share.mongo.base.BaseMongoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.Meta;
import org.springframework.data.mongodb.repository.Query;

import java.util.stream.Stream;

import static org.endless.erp.share.constant.ConstantResource.CURSOR_BATCH_SIZE;


/**
 * GameEveItemRepository
 *
 * @author Deng Haozhi
 * @date 2023/5/15 23:43
 * @since 0.0.2
 */
public interface GameEveItemRepository extends BaseMongoRepository<GameEveItem, String> {
    @Query(value = "{ 'published' : true, 'industryId' : 'game.eve' }", fields = "{ 'itemId': 1 }")
    @Meta(cursorBatchSize = CURSOR_BATCH_SIZE)
    Stream<GameEveItem> findItemIdsStreamByPublished(Pageable pageable);

    @CountQuery(value = "{ 'published' : true, 'industryId' : 'game.eve' }")
    long countByPublished();
}