package org.endless.erp.game.eve.share.ddd.item;

import org.endless.erp.share.mongo.jpa.MongoJpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * GameEveItemJpaRepository
 *
 * @author Deng Haozhi
 * @date 2023/5/15 23:43
 * @since 0.0.2
 */
public interface GameEveItemJpaRepository extends MongoRepository<GameEveItem, String> {
    @Query(value = "{ 'published' : true }")
    Slice<GameEveItem> findSliceByPublished(Pageable pageable);
}