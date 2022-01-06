package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Service.AccountService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getLogin(){
        return "/login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(Model model, String name, String password, HttpServletResponse response){
        int expire = 3600 * 12;
        Map<String,Object> map = accountService.login(name,password,expire);
        if(map.containsKey("ticket"))
        {
            Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
            cookie.setMaxAge(expire);
            response.addCookie(cookie);
            return "/index";
        }else
        {
            model.addAttribute("accountMessage",map.get("accountMessage"));
            model.addAttribute("passwordMessage",map.get("passwordMessage"));
            return "/login";
        }

    }

    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request){
        accountService.logout(request);
        return "/index";
    }
}
