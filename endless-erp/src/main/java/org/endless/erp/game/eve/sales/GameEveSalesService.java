package org.endless.erp.game.eve.sales;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * GameEveSalesService
 *
 * @author Deng Haozhi
 * @date 2023/4/30 1:55
 * @since 0.0.2
 */
@Log4j2
@Service
public class GameEveSalesService {
    //
    // private final GameEveItemService gameEveItemService;
    //
    // private final GameEveSalesUpdateTask gameEveSalesUpdateTask;
    //
    // private final GameEveThreadPoolConfiguration gameEveThreadPoolConfiguration;
    //
    // public GameEveSalesService(GameEveItemService gameEveItemService, GameEveSalesUpdateTask gameEveSalesUpdateTask, GameEveThreadPoolConfiguration gameEveThreadPoolConfiguration) {
    //     this.gameEveItemService = gameEveItemService;
    //     this.gameEveSalesUpdateTask = gameEveSalesUpdateTask;
    //     this.gameEveThreadPoolConfiguration = gameEveThreadPoolConfiguration;
    // }
    //
    //
    // /**
    //  *
    //  **/
    // public void update() {
    //
    //     var begin = System.currentTimeMillis();
    //     log.info("updating!");
    //     log.debug("update main thread begin: " + begin);
    //
    //     var pages = gameEveItemService.getCountByPublished(true) / (PAGE_SIZE / 10) + 1;
    //
    //     log.debug("pages: " + pages);
    //
    //     for (int i = 0; i < gameEveThreadPoolConfiguration.getCorePoolSize(); i++) {
    //         gameEveSalesUpdateTask.update(pages);
    //     }
    //     log.debug("main thread cost : " + (System.currentTimeMillis() - begin));
    // }
}