package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.UserService;
import com.hfz.epidemicmanage.Util.HostHolder;
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

    @RequestMapping(path = "/addinformation",method = RequestMethod.GET)
    public String getInformation(){
        return "/information";
    }

    @RequestMapping(path = "/information",method = RequestMethod.POST)
    public String information(Model model, User user){
        Map<String,Object> map = userService.addUser(user);
        model.addAttribute("userMessage",map.get("userMessage"));

        return "/information";
    }
}
