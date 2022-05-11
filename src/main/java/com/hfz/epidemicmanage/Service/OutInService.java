package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.OutInMapper;
import com.hfz.epidemicmanage.Entity.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class OutInService {

    @Autowired
    OutInMapper outInMapper;

    public String addFlow(Flow outflow)
    {
        if(outflow == null)
        {
            throw new IllegalArgumentException("参数不能为空");
        }
        outInMapper.recoedFlow(outflow);
        return "登记成功";
    }

    public void setewmcode(String place,int code,String ewmpath)
    {
        outInMapper.setErweimaUrl(place,code,ewmpath);
    }

    public List<Flow> findFlowList(int offset,int limit)
    {
        return outInMapper.selectFlowAll(offset,limit);
    }

    public List<Flow> findFlowByPlace(String place,int limit,int offset)
    {
        return outInMapper.selectPlace(place,offset,limit);
    }

    public List<Flow> findFlowByName(String name,int limit,int offset)
    {
        return outInMapper.selectName(name,offset,limit);
    }
}
