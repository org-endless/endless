package org.endless.erp.web.index;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * IndexConfiguration
 * <p>
 * <p>create 2022/11/17 16:56
 * <p>update 2023/8/15 18:34
 *
 * @author Deng Haozhi
 * @see WebMvcConfigurer
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class IndexConfiguration implements WebMvcConfigurer {

    /**
     * @param registry ViewControllerRegistry
     **/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("signin");
        registry.addViewController("/signin").setViewName("signin");
        registry.addViewController("/index").setViewName("signin");
    }
}
