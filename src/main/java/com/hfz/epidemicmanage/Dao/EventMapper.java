package com.hfz.epidemicmanage.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EventMapper {

    int findPatientNum(String status);//查询人数


}
