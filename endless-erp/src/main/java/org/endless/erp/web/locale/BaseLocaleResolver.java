package org.endless.erp.web.locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * BaseLocaleResolver
 * <p>国际化语言格式转化
 * <p>Resolve locale for i18n.
 *
 * @author Deng Haozhi
 * @date 2022/11/17 16:52
 * @since 0.0.1
 */
@Log4j2
public class BaseLocaleResolver implements LocaleResolver {

    /**
     * resolveLocale
     * 
     * @param request HttpServletRequest
     * 
     * @return java.util.Locale
     **/
    @Override
    public @NonNull Locale resolveLocale(HttpServletRequest request) {

        String lang = request.getParameter("lang");
        if (StringUtils.hasLength(lang)) {
            log.trace("Lang is " + lang);
            var split = lang.split("_");
            return Locale.of(split[0], split[1]);
        }

        Locale defaultLocale = Locale.getDefault();
        if (defaultLocale != null) {
            log.trace("Default Locale is " + defaultLocale);
            return defaultLocale;
        }
        return request.getLocale();
    }

    @Override
    public void setLocale(@NonNull HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }

}