package org.endless.erp.web.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author Deng Haozhi
 * @date 2023/5/19 16:03
 * @since 0.0.2
 */
@Configuration
public class BaseLocaleConfiguration {

  /**
   * @return org.springframework.web.servlet.BaseLocaleResolver
   */
  @Bean
  public LocaleResolver localeResolver() {
    return new BaseLocaleResolver();
  }
}
