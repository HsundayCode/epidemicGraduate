package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.UserService;
import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    HostHolder hostHolder;

    @LoginRequire
    @RequestMapping(path = "/addinformation",method = RequestMethod.GET)
    public String getInformation(){
        return "/information";
    }

    //用户添加信息
    //需要判断用户是否已经存在信息，不能重复提交，判断账号id是否在user里存在
    //添加完信息后应该返回首页
    @LoginRequire
    @RequestMapping(path = "/information",method = RequestMethod.POST)
    public String information(Model model, User user){
        Map<String,Object> map = userService.addUser(user);
        //判断map是否为空，返回指定页面
        if(map != null)
        {
            model.addAttribute("userMessage",map.get("userMessage"));
            return "/information";
        }
       return "/index";
    }
}
