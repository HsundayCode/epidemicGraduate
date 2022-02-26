package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Entity.Event;
import com.hfz.epidemicmanage.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    //这里不应该返回到首页，用ajax
    //获取社区病人数量
    @RequestMapping(path = "/getpatientNum",method = RequestMethod.GET)
    public String getPatientNum(Model model){
        Event event = eventService.getPatientNum();
        model.addAttribute("event",event);
        return "views/console";
    }

}
