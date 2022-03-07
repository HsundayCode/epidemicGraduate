package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
import com.hfz.epidemicmanage.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements EpidemicConstant {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    HostHolder hostHolder;




    //添加个人信息
    public Map<String,Object> addUser(User user){
        Map<String,Object> map = new HashMap<>();
        Account account = hostHolder.getAccount();
        if(account == null)
        {
            map.put("userMessage","请先登录帐号");
            return map;
        }
        if(user == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        user.setAccountid(account.getId());
        userMapper.insertUser(user);
        accountMapper.updateStatus(account.getId(),INFORMATION_PERFECT);
        map.put("userMessage","添加成功");
        return map;
    }

    //获得用户列表页面
    //一次性查询 列表
    public List<Map<String,Object>> findUsers(int limit, int offset){

        List<User> patientslist = userMapper.selectUsers(limit,offset);
        List<Map<String,Object>> reslist = new ArrayList<>();
        for(User user : patientslist)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("account",accountMapper.selectById(user.getAccountid()));
            map.put("user",user);
            reslist.add(map);
        }
        return reslist;
    }

    public List<Map<String,Object>> findUserByName(String name)
    {
        List<User> ByNameList =userMapper.selectByName(name);
        List<Map<String,Object>> reslist = new ArrayList<>();
        for(User user : ByNameList)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("account",accountMapper.selectById(user.getAccountid()));
            map.put("user",user);
            reslist.add(map);
        }
        return reslist;
    }

    public List<Map<String,Object>> findUserByIdcard(int idcard)
    {
        Map<String,Object> map = new HashMap<>();
        User patient = userMapper.selectByIdcard(idcard);
        map.put("user",patient);
        map.put("account",accountMapper.selectById(patient.getAccountid()));
        List<Map<String,Object>> reslist = new ArrayList<>();
        reslist.add(map);
        return reslist;
    }

    //单个精确查询 用户详情
    public User findUserById(int id){
        return userMapper.selectById(id);
    }
    //用于判断用户不可以（修改）二次输入信息
    //添加用户信息
    //修该用户信息
//    public void updateUser(int userid,String status,String place,String divide,String trail,String occurrencetime)
//    {
//        userMapper.updateUser(userid,status,place,divide,trail,occurrencetime);
//    }
    //修改用户状态
    public void updateStatus(int id,String status)
    {
        userMapper.updateStatus(id,status);
    }

    public void deleteUser(int id)
    {
        userMapper.deleteUser(id);
    }

}
