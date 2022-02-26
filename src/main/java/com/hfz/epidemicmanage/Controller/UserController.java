package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.UserService;
import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
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
        return "addTemplate/addUser";
    }

    //用户添加信息
    //需要判断用户是否已经存在信息，不能重复提交，判断账号id是否在user里存在
    //添加完信息后应该返回首页
    @LoginRequire
    @RequestMapping(path = "/information",method = RequestMethod.POST)
    public String information(Model model, User user){
        Map<String,Object> map = new HashMap<>();
        Account account = hostHolder.getAccount();
        if(account.getStatus() == 3)
        {
            map.put("userMessage","信息重复添加");
            model.addAttribute("res",map.get("userMessage"));
            return "/addresult";
        }
       map = userService.addUser(user);
        //判断map是否为空，返回指定页面
        if(map != null)
        {
            model.addAttribute("userMessage",map.get("userMessage"));
            return "/userdetail";
        }
       return "index";
    }

    @RequestMapping(path = "/deleteuser/{userid}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userid") int id, Model model)
    {
        userService.deleteUser(id);
        model.addAttribute("res","删除成功");
        return "/addresult";
    }
}
