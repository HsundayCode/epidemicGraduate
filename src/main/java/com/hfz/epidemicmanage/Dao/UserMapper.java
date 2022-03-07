package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //因为用户表和病人表一起的 两个查询方法重复了
    User selectById(int id);
    List<User> selectByName(String name,int limit,int offset);
    User selectByAccountid(int accountid);
    int insertUser(User user);//用户信息添加
    int deleteUser(int id);
    User selectByIdcard(int idcard);//身份证
    List<User> selectUsers(@Param("limit") int limit, @Param("offset") int offset);//全部
    int updateStatus(int userid,String Status);//修改级别

    int updateUser();
}
