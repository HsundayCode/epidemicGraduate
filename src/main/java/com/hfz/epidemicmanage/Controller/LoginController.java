package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.AccountService;
import com.hfz.epidemicmanage.Service.EventService;
import com.hfz.epidemicmanage.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
//登录登出
@Controller
public class LoginController {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    EventService eventService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserMapper userMapper;


    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getLogin(){
        return "login";
    }

    //登录，登陆凭证，cookie
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(Model model, String name, String password, HttpServletResponse response){
        int expire = 3600 * 12;
        Map<String,Object> map = accountService.login(name,password,expire);//判断账号合法
        if(map.containsKey("ticket"))
        {
            Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
            cookie.setMaxAge(expire);
            response.addCookie(cookie);
            return "redirect:/index";//单纯的return是直接找到页面，而重定向是再次请求，动态页面要重定向，不然之前的数据会丢失
        }else
        {
            model.addAttribute("accountMessage",map.get("accountMessage"));
            model.addAttribute("passwordMessage",map.get("passwordMessage"));
            return "login";//返回注册状态
        }

    }

    //通过cookie来设置登出 设置登录凭证的状态
    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request){
        accountService.logout(request);
        return "index";
    }
}
