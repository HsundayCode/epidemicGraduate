package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //因为用户表和病人表一起的 两个查询方法重复了
    User selectById(int id);
    User selectByName(String name);
    User selectByAccountid(int accountid);
    int insertUser(User user);//用户信息添加
}
