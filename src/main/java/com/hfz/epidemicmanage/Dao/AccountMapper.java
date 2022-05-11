package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper {

    Account selectById(int id);//根据id查询账号

    Account selectByName(String name);//登录账号 根据id查询账号

    int insertAccount(Account account);//注册账号 以后注册用redis步直接入数据库，激活后再入数据库

    Account selectByEmail(String email);//通过邮箱激活

    int updateStatus(@Param("id") int id, @Param("status") int status);//状态改变 激活

    List<Account> selectManage();
    void updateType(int type,int id);
    List<Account> selectNormal();

    int deleteAccount(int id);

    int changepasw(String pasw,String name,String salt);
}
