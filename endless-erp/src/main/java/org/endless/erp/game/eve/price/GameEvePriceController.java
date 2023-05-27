package org.endless.erp.game.eve.price;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GameEvePriceController
 *
 * @author Deng Haozhi
 * @date 2023/5/19 16:00
 * @since 0.0.3
 */
@Log4j2
@RestController
@RequestMapping("/game/eve/price")
public class GameEvePriceController {

    private final GameEvePriceService gameEvePriceService;

    public GameEvePriceController(GameEvePriceService gameEvePriceService) {
        this.gameEvePriceService = gameEvePriceService;
    }

    @GetMapping("/update")
    @PostMapping("/update")
    public String update() {

        String result = "success";
        // gameEvePriceService.update();
        return result;
    }
}