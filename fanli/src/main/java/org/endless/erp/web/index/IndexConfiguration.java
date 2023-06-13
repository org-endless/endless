package org.endless.erp.web.index;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * IndexConfiguration
 *
 * @author Deng Haozhi
 * @date 2022/11/17 16:56
 * @see WebMvcConfigurer
 * @since 0.0.1
 */
@Configuration
public class IndexConfiguration implements WebMvcConfigurer {

    /**
     *
     * @param registry ViewControllerRegistry
     **/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("signin");
        registry.addViewController("/signin").setViewName("signin");
        registry.addViewController("/index").setViewName("signin");
    }
}
