package org.endless.erp.game.eve.market.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * GameEveMarketOrderController
 *
 * @author Deng Haozhi
 * @date 2023/3/16 18:32
 * @since 0.0.2
 */
@Slf4j
@RestController
@RequestMapping("/game/eve/market/order")
public class GameEveMarketOrderController {
    private final GameEveMarketOrderService gameEveMarketOrderService;

    public GameEveMarketOrderController(GameEveMarketOrderService gameEveMarketOrderService) {
        this.gameEveMarketOrderService = gameEveMarketOrderService;
    }

    @GetMapping("/save")
    @PostMapping("/save")
    @ResponseBody
    public String regionOrders() {
        try {
            gameEveMarketOrderService.save();
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "error";
        }
    }
}
