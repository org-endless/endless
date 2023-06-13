package org.endless.erp.game.eve.item;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.ddd.item.ItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GameEveItemController
 * <p>游戏EVE物品/商品视图控制器
 * <p>Game EVE item view controller
 * <p>
 * <p>create 2023/03/12 22:20
 * <p>update 2023/05/28 00:34
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Log4j2
@RestController
@RequestMapping("/game/eve/item")
public class GameEveItemController {

    private final ItemService itemService;

    public GameEveItemController(@Qualifier("gameEveItemService") ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/load")
    @PostMapping("/load")
    public String load() {

        String result = "success";
        itemService.load("data/eve/typeIDs.yaml");
        return result;
    }
}