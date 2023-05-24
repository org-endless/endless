package org.endless.erp.game.eve.share.ddd.item;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.ddd.item.ItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GameEveItemController
 *
 * @author Deng Haozhi
 * @date 2023/3/12 22:20
 * @since 0.0.2
 */
@Log4j2
@RestController
@RequestMapping("/game/eve/item")
public class GameEveItemController {

    @Qualifier("GameEveItemService")
    private final ItemService itemService;

    public GameEveItemController(ItemService itemService) {
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