package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.OutInMapper;
import com.hfz.epidemicmanage.Entity.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class OutInService {

    @Autowired
    OutInMapper outInMapper;

    public String addFlow(Flow outflow)
    {
        outInMapper.recoedFlow(outflow);
        return "登记成功";
    }


    public void setewmcode(String place,int code)
    {
        outInMapper.setErweimaUrl(place,code);
    }
}
