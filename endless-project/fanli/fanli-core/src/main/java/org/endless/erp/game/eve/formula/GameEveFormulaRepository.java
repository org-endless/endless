package org.endless.erp.game.eve.formula;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * GameEveFormulaRepository
 * <p>
 * <p>update 2023/9/27 10:27
 * <p>update 2023/9/27 10:27
 *
 * @author Deng Haozhi
 * @see MongoRepository
 * @since 0.0.4
 */
@Repository
public interface GameEveFormulaRepository extends MongoRepository<GameEveFormula, String> {
}
