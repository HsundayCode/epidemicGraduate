package com.hfz.epidemicmanage.Controller.Interceptor;

import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginRequireInterceptor implements HandlerInterceptor {

    @Autowired
    HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LoginRequire loginRequire = method.getAnnotation(LoginRequire.class);
            if(loginRequire != null && hostHolder.getAccount() == null)
            {
                response.sendRedirect("/index");
                return false;
            }
        }
    return true;
    }

}
