package org.endless.erp.share.ddd.item;

/**
 * ItemService
 * <p>物品/商品服务
 * <p>The Item Service for ERP.
 *
 * @author Deng Haozhi
 * @date 2023/5/13 21:36
 * @since 0.0.3
 */
public interface ItemService{

    default <T> void load(T argument) {
    }

    default <T> void load(T[] arguments) {
    }
}