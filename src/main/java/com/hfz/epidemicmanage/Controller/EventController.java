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

    @RequestMapping(path = "/getpatientNum",method = RequestMethod.GET)
    public String getPatientNum(Model model){
        Event event = eventService.getPatientNum();
        model.addAttribute("event",event);
        return "/index";
    }

}
