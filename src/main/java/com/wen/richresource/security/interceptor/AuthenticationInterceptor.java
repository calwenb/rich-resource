package com.wen.richresource.security.interceptor;

import com.alibaba.fastjson2.JSON;
import com.wen.richresource.annotation.PassAuth;
import com.wen.richresource.service.AuthService;
import com.wen.richresource.util.LoggerUtil;
import com.wen.richresource.util.ResultUtil;
import com.wen.richresource.vo.ResultVO;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * token验证拦截器类
 * 用于后端接口的验证，
 * 调用后端接口需携带token进行身份验证，
 * 使用@PassAuth注解则跳过拦截器
 *
 * @author calwen
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    AuthService authService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        boolean b = object instanceof HandlerMethod;
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(PassAuth.class)) {
            PassAuth passAuth = method.getAnnotation(PassAuth.class);
            if (passAuth.required()) {
                return true;
            }
        }
        try {
            // 执行认证,redis中若有此token，则放行
            if (authService.verifyToken()) {
                if (authService.getExpireTime() < TimeUnit.HOURS.toSeconds(1)) {
                    authService.renew(2);
                }
                return true;
            }
        } catch (Exception e) {
            LoggerUtil.warn(e.getMessage(), AuthenticationInterceptor.class);
        }
        ResultVO<String> resultVO = ResultUtil.unauthorized();
        response.getWriter().println(JSON.toJSONString(resultVO));
        return false;
    }

}