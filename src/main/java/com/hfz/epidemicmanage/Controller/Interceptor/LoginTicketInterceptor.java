package com.hfz.epidemicmanage.Controller.Interceptor;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.LoginTicket;
import com.hfz.epidemicmanage.Util.CookieUtil;
import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.Util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtil.getCookie(request,"ticket");
        String ticketKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket = (LoginTicket) redisTemplate.opsForSet().pop(ticketKey);
        if(ticket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date()))
        {

            Account account = accountMapper.selectById(loginTicket.getId());
            hostHolder.setAccount(account);
        }
        return true;
    }
}
