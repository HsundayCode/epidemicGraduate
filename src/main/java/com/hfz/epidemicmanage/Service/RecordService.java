package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.RecordSMapper;
import com.hfz.epidemicmanage.Entity.Record;
import com.hfz.epidemicmanage.Util.DateUtil;
import com.hfz.epidemicmanage.Util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecordService {
    @Autowired
    RecordSMapper recordSMapper;
    @Autowired
    RedisTemplate redisTemplate;

    //某人历史打卡记录
    public List<Record> findHistoryRecordList(int accountid,int limit,int offset){
        List<Record> HistoryRecordList = recordSMapper.selectHistoryRecord(accountid,limit,offset);
        return HistoryRecordList;
    }

    public List<Record> findRecentlyRecordList(int limit,int offset){
        return recordSMapper.selectTodayRecordList(limit,offset);
    }

    //添加记录
    public String addRecord(Record record){
        String todayKey = RedisKeyUtil.getTodayRecordKey(record.getAccountid());
        String beforeDate = (String)redisTemplate.opsForValue().get(todayKey);//获取缓存日期

        boolean between = DateUtil.getDifferentDateGap(beforeDate,record.getCreatetime());//判断日期是否过期,过了一天返回true
        if(beforeDate == null || between)//缓存数据不存在或日期已经是另一天了>可以登记
        {
            redisTemplate.opsForValue().set(todayKey,record.getCreatetime());//更新缓存数据
            if(recordSMapper.selectTodayRecord(record.getAccountid()) != null)//判断是否第一次登记
            {
                //更新最近表
                recordSMapper.updateToday(record.getAccountid(),record.getTemperature(),record.getLocale(),record.getCreatetime());
            }else {
                recordSMapper.insertTodayRecord(record);//没有则放入最近表
            }

        }else {
            return "一天内不可登记多次";
        }
        recordSMapper.insertRecord(record);//放入历史表
        return "成功登记";
    }

    //先根据姓名查到帐号id
    //查找某用户最近记录
    public Record findRecentlyRecord(int accountid){
        return recordSMapper.selectTodayRecord(accountid);
    }

    //查找当天打卡人数
    public int findTodayRecordRow(String createtime){
        return recordSMapper.selectTodayRecordRow(createtime);
    }

    public List<Record> findRecordtem(int temperature,int limit,int offset){
        return recordSMapper.selectRecordByTemperature(temperature,limit,offset);
    }

    public void deleteRecord(int id)
    {
        recordSMapper.deleteRecord(id);
    }
}
