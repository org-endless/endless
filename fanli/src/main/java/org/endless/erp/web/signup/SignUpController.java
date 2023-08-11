package org.endless.erp.web.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SignUpController
 *
 * @author Deng Haozhi
 * @date 2022/11/17 17:09
 * @since 0.0.2
 */
@Controller
@RequestMapping("/user/signup")
public class SignUpController {

    @GetMapping
    @PostMapping
    public String signUp() {
        return "signup";
    }
}
