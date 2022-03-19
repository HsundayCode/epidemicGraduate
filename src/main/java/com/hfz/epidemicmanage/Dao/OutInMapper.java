package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Flow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
@Mapper
@Repository
public interface OutInMapper {
    int recoedFlow(Flow flow);
    int recordIn(Flow flow);
    int selectPlace(String place);
    List<Flow> selectFlowAll(int limit,int offset);
    List<Flow> selectOut(int flowType,int limit,int offset);
    List<Flow> selectIn(int flowType,int limit,int offset);
    List<Flow> selectRecordTime(Data recordtime,int limit,int offset);
    Integer setErweimaUrl(@Param("place") String place, @Param("code") int code);
    Integer selectErweimacode(String place);
    String selectcode(int code);


}
