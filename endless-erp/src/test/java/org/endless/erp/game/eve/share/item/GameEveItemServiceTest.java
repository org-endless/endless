package org.endless.erp.game.eve.share.item;


import org.endless.erp.game.eve.item.GameEveItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameEveItemServiceTest {
    @Autowired
    private GameEveItemService gameEveItemService;

    @Test
    void test() {
        System.out.println(gameEveItemService.countByPublished());
    }

}