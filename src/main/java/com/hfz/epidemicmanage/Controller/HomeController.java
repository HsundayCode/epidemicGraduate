package com.hfz.epidemicmanage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//登录页面 每日动态消息展示 以及消息通知
@Controller
public class HomeController {
    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndex(){
        return "/index";
    }
}
