package org.endless.erp.game.eve.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GameEvePriceController
 * <p>
 * <p>create 2023/5/19 16:00
 * <p>update 2023/8/15 18:33
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Slf4j
@RestController
@RequestMapping("/game/eve/price")
public class GameEvePriceController {

    // private final GameEvePriceService gameEvePriceService;
    //
    // public GameEvePriceController(GameEvePriceService gameEvePriceService) {
    //     this.gameEvePriceService = gameEvePriceService;
    // }

    @GetMapping("/update")
    @PostMapping("/update")
    public String update() {

        String result = "success";
        // gameEvePriceService.update();
        return result;
    }
}
