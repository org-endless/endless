package org.endless.erp.web.signin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * SignInHandlerInterceptor
 *
 * <p>登录拦截器
 *
 * <p>Interceptor for sign in.
 *
 * @author Deng Haozhi
 * @date 2023/5/14 0:22
 * @since 0.0.3
 */
@Log4j2
public class SignInHandlerInterceptor implements HandlerInterceptor {

  /**
   * 前置拦截器，拦截未登录
   *
   * <p>Pre handler interceptor for sign in failed
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param handler Object
   * @return boolean
   */
  @Override
  public boolean preHandle(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler)
      throws Exception {

    if (request.getSession().getAttribute("signInUser") == null) {

      log.trace("The user is sign in FAILED");
      request.setAttribute("isSignInFailed", true);
      request.getRequestDispatcher("/signin").forward(request, response);
      return false;
    }
    return true;
  }

  @Override
  public void postHandle(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler,
      ModelAndView modelAndView)
      throws Exception {
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler,
      Exception ex)
      throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
