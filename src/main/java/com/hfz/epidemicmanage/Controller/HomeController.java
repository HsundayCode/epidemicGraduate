package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.Activity;
import com.hfz.epidemicmanage.Entity.Event;

import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.ActivityService;
import com.hfz.epidemicmanage.Service.EventService;
import com.hfz.epidemicmanage.Service.PostService;
import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.WebScoket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


//登录页面 每日动态消息展示 以及消息通知
@Controller
public class HomeController {

    @Autowired
    WebSocketServer webSocketServer;
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



    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndex(Model model){
        User user = null;
        Account account = hostHolder.getAccount();
        if(account != null)
        {
            user = userMapper.selectByAccountid(account.getId());
        }



        model.addAttribute("account",account);
        model.addAttribute("user",user);
        return "/index";
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

//    @RequestMapping(path = "/message",method = RequestMethod.POST)
//    public String sendMessageTest(Model model,String content){
//        System.out.println(content);
//
//
//        return "/WebsocketClient";
//    }
}
