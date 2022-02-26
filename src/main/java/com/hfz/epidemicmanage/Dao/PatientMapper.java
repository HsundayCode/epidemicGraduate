package com.hfz.epidemicmanage.Dao;


import com.hfz.epidemicmanage.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatientMapper {
    User selectById(int userid);//病人id 用户id
    User selectByIdcard(int idcard);//身份证

    List<User>selectByName(String name,@Param("limit") int limit,@Param("offset") int offset);//条件 关键字查询
    List<User>selectByStatus(int status,int limit,int offset);



    List<User> selectPatients(@Param("limit") int limit,@Param("offset") int offset);//全部

    int insertPatient(User patient);//添加--用更新信息 添加👉用户自己添加

    int updateStatus(int userid,String Status);//修改级别

    int updatePatient(@Param("userid") int userid, @Param("status") String status,@Param("place") String place,
                      @Param("divide") String divide, @Param("trail") String trail,
                      @Param("occurrencetime") String occurrencetime);//管理员更新病人信息


}
