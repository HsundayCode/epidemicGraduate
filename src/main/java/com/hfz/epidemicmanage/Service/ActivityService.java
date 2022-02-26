package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Dao.ActivityMapper;
import com.hfz.epidemicmanage.Entity.Activity;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService implements EpidemicConstant {
    @Autowired
    ActivityMapper activityMapper;

    public List<Activity> findActivityByStatus(int status,int limit,int offset){
        return activityMapper.selectActivityByStatus(status,limit,offset);
    }

    public List<Activity> findActivityByActime(String actime,int limit,int offset){
        return activityMapper.selectActivityByActime(actime,limit,offset);
    }

    public List<Activity> findActivityByPlace(String place,int limit,int offset){
        return activityMapper.selectActivityByPlace(place,limit,offset);
    }

    public List<Activity> findActivityAll(int limit,int offset)
    {
        return activityMapper.selectAll(limit,offset);
    }

    public Activity findActivityByTitle(String title){
        return activityMapper.selectActivityByTitle(title);
    }

    public Activity findActivityById(int id){
        return activityMapper.selectActivityById(id);
    }

    public void addActivity(Activity activity){
        System.out.println(activity.getActime());
        activityMapper.insertActivity(activity);
    }

    public void deleteActivity(int id)
    {
        activityMapper.deleteActivity(id);
    }

    //0未开始-》1开始  1开始 -》 2结束 2 结束-》0未开始
    public void updateAcStatus(int status,int id){
        if(status == 0)
        {
            activityMapper.updateStatus(AC_START,id);//未开始修成开始 1 -
        }else if(status == 1) {
            activityMapper.updateStatus(AC_END,id);//开始修成结束
        }else if(status == 2)
        {
            activityMapper.updateStatus(AC_UNSTART,id);//结束修成未开始
        }

    }
}
