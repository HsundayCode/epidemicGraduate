package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Entity.Event;
import com.hfz.epidemicmanage.Service.EventService;
import com.hfz.epidemicmanage.WebScoket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//登录页面 每日动态消息展示 以及消息通知
@Controller
public class HomeController {

    @Autowired
    WebSocketServer webSocketServer;
    @Autowired
    EventService eventService;


    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndex(Model model){
//        Event event = eventService.getPatientNum();
//        model.addAttribute("event",event);
        return "index";
    }

//    @RequestMapping(path = "/message",method = RequestMethod.POST)
//    public String sendMessageTest(Model model,String content){
//        System.out.println(content);
//
//
//        return "/WebsocketClient";
//    }
}
