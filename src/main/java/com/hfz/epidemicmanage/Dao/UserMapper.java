package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User selectById(int id);
    User selectByName(String name);
    User selectByAccountid(int accountid);
    int insertUser(User user);
}
