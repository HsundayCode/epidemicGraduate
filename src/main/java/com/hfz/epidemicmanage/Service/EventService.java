package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.EventMapper;
import com.hfz.epidemicmanage.Entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventService {

    @Autowired
    EventMapper eventMapper;

    public Event getPatientNum(){
        Event event = new Event();
        event.setCreatetime(new Date());
        event.setDeathNum(eventMapper.findPatientNum("死亡"));
        event.setDivideNum(eventMapper.findPatientNum("隔离"));
        event.setDoubtNUm(eventMapper.findPatientNum("感染"));
        return event;
    }
}
