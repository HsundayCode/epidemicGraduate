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
    List<Flow> selectPlace(String place,int offset,int limit);
    List<Flow> selectFlowAll(@Param("offset") int offset,@Param("limit") int limit);
    List<Flow> selectName(String name,int offset,int limit);
    Integer setErweimaUrl(@Param("place") String place, @Param("code") int code,String ewmpath);
    Integer selectErweimacode(String place);
    String selectcode(int code);



}
