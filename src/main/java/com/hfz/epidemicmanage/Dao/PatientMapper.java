package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Patient;
import com.hfz.epidemicmanage.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatientMapper {
    User selectById(int userid);//病人id
    User selectByIdcard(int idcard);//身份证

    List<User>selectByKey(@Param("key") String key,@Param("limit") int limit,@Param("offset") int offset);//名字
    List<User> selectByPlace(String place,int limit,int offset);//感染地点
    List<User> selectByStatus(String status,int limit,int offset);//隔离情况级别

    List<User> selectPatients(@Param("limit") int limit,@Param("offset") int offset);//全部

    int insertPatient(User patient);//添加
    int updateStatus(String Status);//修改级别
    int updatePatient(@Param("userid") int userid, @Param("status") String status,@Param("place") String place,
                      @Param("divide") String divide, @Param("trail") String trail, @Param("occurrencetime") String occurrencetime);//在user表里更新病人信息


}
