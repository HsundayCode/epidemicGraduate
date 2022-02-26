package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RecordSMapper {
    //某人历史打卡记录
    List<Record> selectHistoryRecord(@Param("accountid") int accountid,@Param("limit")int limit,@Param("offset")int offset);
    //插入记录
    int insertRecord(Record record);

    //当天插入记录表
    int insertTodayRecord(Record record);
    //最近记录
    Record selectTodayRecord(int accountid);
    //更新当天记录表
    int updateToday(@Param("accountid") int accountid, @Param("temperature") String
            temperature, @Param("locale") String locale, @Param("createtime") String createtime);
    //查询所有最近记录
    List<Record> selectTodayRecordList(@Param("limit") int limit,@Param("offset")int offset);
    //查询当天记录人数
    int selectTodayRecordRow(String createtime);


}
