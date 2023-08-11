package org.endless.erp.game.eve.formula;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GameEveFormulaController
 *
 * @author Deng Haozhi
 * @date 2023/4/11 19:39
 * @since 0.0.2
 */
@RestController
@RequestMapping("/game/eve/formula")
public class GameEveFormulaController {
    private final GameEveFormulaService gameEveFormulaService;

    public GameEveFormulaController(GameEveFormulaService gameEveFormulaService) {
        this.gameEveFormulaService = gameEveFormulaService;
    }

    @GetMapping("/load")
    @PostMapping("/load")
    public String load() {

        var result = "success";
        gameEveFormulaService.load("data/eve/blueprints.yaml");
        gameEveFormulaService.load("data/eve/planetSchematics.yaml", GameEveFormula.Categories.planet);
        return result;
    }
}
