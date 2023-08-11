package org.endless.erp.game.eve.market.order;

import org.springframework.web.bind.annotation.*;

/**
 * GameEveMarketOrderController
 *
 * @author Deng Haozhi
 * @date 2023/3/16 18:32
 * @since 0.0.2
 */
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
            e.printStackTrace();
            return "error";
        }
    }
}
