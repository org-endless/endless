package org.endless.erp.web.interceptor;

import org.endless.erp.web.signin.SignInHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * BaseInterceptorConfiguration
 *
 * @author Deng Haozhi
 * @date 2023/5/19 16:04
 * @since 0.0.3
 */
@Configuration
public class BaseInterceptorConfiguration implements WebMvcConfigurer {

    /**
     * addInterceptors
     *
     * @param registry InterceptorRegistry
     *
     **/
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(new SignInHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/game/eve/**", "/", "/index", "/signin", "/user/**", "/error", "/css/**" ,"/img/**", "/js/**", "/plugins/**");
    }
}