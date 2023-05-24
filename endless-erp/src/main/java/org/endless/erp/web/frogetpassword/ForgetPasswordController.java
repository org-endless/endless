package org.endless.erp.web.frogetpassword;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * ForgetPasswordController
 *
 * @author Deng Haozhi
 * @date 2022/11/17 16:56
 * @since 0.0.1
 */
@Controller
@RequestMapping("/user/forgetpassword")
public class ForgetPasswordController {

    @GetMapping
    @PostMapping
    public String forgetPassword() {
        return "forgetpassword";
    }

}