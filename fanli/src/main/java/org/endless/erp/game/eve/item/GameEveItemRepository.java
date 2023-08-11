package org.endless.erp.game.eve.item;

import org.endless.data.mongo.base.BaseMongoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.Meta;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * GameEveItemRepository
 * <p>游戏EVE物品/商品数据库接口
 * <p>Game EVE item database interface.
 * <p>
 * <p>create 2023/5/15 23:43
 * <p>update 2023/05/27 19:57
 *
 * @author Deng Haozhi
 * @see BaseMongoRepository
 * @since 0.0.3
 */
@Repository
public interface GameEveItemRepository extends BaseMongoRepository<GameEveItem, String> {

    /**
     * 分页查询并生成已发布的物品/商品编号数据流
     * <p>Query and generate a published item id data stream pageable.
     *
     * @param pageable 分页
     * @return {@link Stream}<{@link GameEveItem}>
     */
    @Query(value = "{ 'published' : true, 'industryId' : 'game.eve' }", fields = "{ 'itemId': 1 }")
    @Meta(cursorBatchSize = 1000)
    Slice<GameEveItem> findItemIdSliceByPublished(Pageable pageable);

    /**
     * 查询并计算已发布的物品/商品数量
     * <p>Query and calculate the number of items that have been released.
     *
     * @return long
     */
    @CountQuery(value = "{ 'published' : true, 'industryId' : 'game.eve' }")
    long countByPublished();
}
