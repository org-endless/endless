package org.endless.erp.game.eve.market.saleHistory;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

/**
 * GameEveMarketSaleHistoryController
 *
 * <p>create 2023/05/26 16:15
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Log4j2
@RestController
@RequestMapping("/game/eve/market/sale/history")
public class GameEveMarketSaleHistoryController {

    private final GameEveMarketSaleHistoryService gameEveMarketSaleHistoryService;

    public GameEveMarketSaleHistoryController(GameEveMarketSaleHistoryService gameEveMarketSaleHistoryService) {
        this.gameEveMarketSaleHistoryService = gameEveMarketSaleHistoryService;
    }

    @GetMapping("/save")
    @PostMapping("/save")
    @ResponseBody
    public String save() {
        try {
            gameEveMarketSaleHistoryService.save();
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "error";
        }
    }
}
