package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.OutsidersMapper;
import com.hfz.epidemicmanage.Entity.Outsider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutsiderService {
    @Autowired
    OutsidersMapper outsidersMapper;

    public List<Outsider> findOutsiderAll(int limit,int offset)
    {
        return outsidersMapper.selectOutsiderAll(offset,limit);
    }

    public List<Outsider> findOutsiderByName(String name,int limit,int offset)
    {
        return outsidersMapper.selectOutsiderByName(name, limit, offset);
    }

    public List<Outsider> findOutsiderBySource(String source,int limit,int offset)
    {
        return outsidersMapper.selectOutsiderBySource(source, limit, offset);
    }

    public List<Outsider>findOutsiderByIdcard(String idcard, int limit, int offset)
    {
        return outsidersMapper.selectOutsiderByIdcard(idcard,limit,offset);
    }

    public void addOutsider(Outsider outsider)
    {
        if(outsider == null)
        {
            throw new IllegalArgumentException("参数不能为空");
        }
        outsidersMapper.insertOutsider(outsider);
    }
    public void deleteOutsider(int id)
    {
        outsidersMapper.deleteOutsider(id);
    }

    public Outsider getDetail(int id)
    {
        return outsidersMapper.selectById(id);
    }
}
