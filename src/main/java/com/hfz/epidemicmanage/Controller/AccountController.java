package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
//注册激活账号
@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(path = "/regist",method = RequestMethod.GET)
    public String toregist(){
        return "/register";
    }
    //注册
    @RequestMapping(path= "/register",method = RequestMethod.POST)
    public String Register(Model model, Account account)
    {
        Map<String,Object> map;
        map = accountService.addAccount(account);//返回账号状态
        if(map==null || map.isEmpty())
        {
            model.addAttribute("msg","注册成功");
            model.addAttribute("target","/index");
            return "/register";
        }else
        {
            model.addAttribute("nameMessage",map.get("nameMessage"));
            model.addAttribute("passwordMessage",map.get("passwordMessage"));
            model.addAttribute("emailMessage",map.get("emailMessage"));
        }
        return "/register";
    }

    //邮箱激活
    @RequestMapping(path= "/register/{Activationcode}",method = RequestMethod.GET)
    public String activationAccount(Model model, @PathVariable("Activationcode") String Activationcode){
        int status = accountService.activation(Activationcode);//返回激活状态码
        if(status == 1)
        {
            model.addAttribute("msg","账号激活成功");
            model.addAttribute("target","/index");
        }else if(status == 0)
        {
            model.addAttribute("msg","账号重复激活");
            model.addAttribute("target","/index");
        }else
        {
            model.addAttribute("msg","账号激活失败");
            model.addAttribute("target","/index");
        }

        return "/regist_result";
    }
}
