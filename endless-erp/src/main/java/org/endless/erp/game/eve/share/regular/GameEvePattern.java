package org.endless.erp.game.eve.share.regular;

import java.util.regex.Pattern;

/**
 * GameEvePattern
 *
 * @author Deng Haozhi
 * @date 2023/5/19 16:31
 * @since 0.0.3
 */
public class GameEvePattern {
    public static Pattern getFile(){
        return GameEveRegular.get(GameEveRegular.EVE_YAML, GameEveRegular.EVE_YAML_FLAG);
    }
}