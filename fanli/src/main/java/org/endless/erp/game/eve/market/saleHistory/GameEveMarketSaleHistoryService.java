package org.endless.erp.game.eve.market.saleHistory;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.game.eve.item.GameEveItemService;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.constant.ConstantResource;
import org.endless.erp.share.ddd.saleHistory.SaleHistoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * GameEveMarketSaleHistoryService
 *
 * <p>create 2023/05/26 16:16
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Log4j2
@Service
public class GameEveMarketSaleHistoryService implements SaleHistoryService {

    private final GameEveAsyncTask gameEveAsyncTask;

    private final GameEveItemService itemService;

    public GameEveMarketSaleHistoryService(
            @Qualifier("gameEveMarketSaleHistoryTask") GameEveAsyncTask gameEveAsyncTask,
            GameEveItemService itemService) {
        this.gameEveAsyncTask = gameEveAsyncTask;
        this.itemService = itemService;
    }


    @Override
    public void save() {

        var begin = System.currentTimeMillis();
        log.info("Saving!");
        log.debug("Save main thread begin: " + begin);

        Pageable pageable = PageRequest.of(0, ConstantResource.PAGE_SIZE);

        while (true) {
            var slice = itemService.findItemIdSliceByPublished(pageable);
            gameEveAsyncTask.run(slice.getContent());

            if (!slice.hasNext()) {
                break;
            }
            pageable = slice.nextPageable();


        }

        log.info("Saved executed!");
        log.debug("Save main thread cost : " + (System.currentTimeMillis() - begin));
    }
}
