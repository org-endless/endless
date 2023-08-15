package org.endless.erp.game.eve.formula;

import org.endless.spring.boot.data.mongo.base.BaseMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * GameEveFormulaRepository
 *
 * @author Deng Haozhi
 * @date 2023/4/11 21:42
 * @since 0.0.2
 */
@Repository
public interface GameEveFormulaRepository extends BaseMongoRepository<GameEveFormula, String> {
}
