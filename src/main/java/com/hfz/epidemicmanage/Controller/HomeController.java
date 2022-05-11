package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.OutInMapper;
import com.hfz.epidemicmanage.Dao.OutsidersMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.*;

import com.hfz.epidemicmanage.Service.ActivityService;
import com.hfz.epidemicmanage.Service.EventService;
import com.hfz.epidemicmanage.Service.PostService;
import com.hfz.epidemicmanage.Util.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


//登录页面 每日动态消息展示 以及消息通知
@Controller
public class HomeController {
    
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    EventService eventService;

    @Autowired
    PostService postService;
    @Autowired
    ActivityService activityService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OutsidersMapper outsidersMapper;



    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndex(Model model){
        User user = null;
        Account account;
        Outsider outsider = null;
        account = hostHolder.getAccount();
        if(account !=null && account.getStatus() == 3)
        {
            user = userMapper.selectByAccountid(account.getId());
            if(user == null)
            {
                outsider = outsidersMapper.selectOutsiderByAccountid(account.getId());
            }

        }
        model.addAttribute("account",account);
        model.addAttribute("user",user);
        model.addAttribute("outsider",outsider);
        return "index";
    }

    @RequestMapping(path = "/getconsole",method = RequestMethod.GET)
    public String getConsole(Model model)
    {
        Event event = eventService.getPatientNum();
        List<Activity> activityList = activityService.findActivityAll(5,0);
        model.addAttribute("activitylist",activityList);
        model.addAttribute("event",event);
        return "views/console";
    }

    @RequestMapping(path = "/err",method = RequestMethod.GET)
    public String getErrorPage()
    {
        return "/error/404";
    }


}
