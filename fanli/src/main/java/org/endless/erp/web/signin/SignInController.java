package org.endless.erp.web.signin;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SignInController
 *
 * @author Deng Haozhi
 * @date 2022/11/17 17:09
 * @since 0.0.2
 */
@Log4j2
@Controller
@RequestMapping("/user/signin")
public class SignInController {

  /**
   * signIn
   *
   * @param username String
   * @param password String
   * @param model Model
   * @param session HttpSession
   * @return java.lang.String
   */
  @GetMapping
  @PostMapping
  public String signIn(
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      Model model,
      HttpSession session) {

    if (StringUtils.hasText(username) && "123456".equals(password)) {

      log.debug("User: >>" + username + "<< is validated SUCCESS!");
      session.setAttribute("signInUser", username);
      return "dashboard";
    } else {

      log.debug("The user is validated FAILED!");
      model.addAttribute("isValidationFailed", true);
      return "signin";
    }
  }
}
