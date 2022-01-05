package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {

    Account selectById(int id);

    Account selectByName(String name);//登录账号

    int insertAccount(Account account);//注册账号

    Account selectByEmail(String email);//通过邮箱激活

    int updateStatus(@Param("id") int id, @Param("status") int status);//状态改变
}
