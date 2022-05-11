package com.hfz.epidemicmanage.Controller.Interceptor;

import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.annotation.ManageRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ManageRequireInterceptor implements HandlerInterceptor {
    @Autowired
    HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            ManageRequire manageRequire = method.getAnnotation(ManageRequire.class);
            if(manageRequire != null && hostHolder.getAccount().getType() != 1 && hostHolder.getAccount().getType() != 3
            && hostHolder.getAccount() == null)
            {
                response.sendRedirect("/login");
                return false;
            }

        }
        return true;
    }
}
