package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
import com.hfz.epidemicmanage.Util.EpidemicUtil;
import com.hfz.epidemicmanage.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        //account.setStatus(INFORMATION_PERFECT);
        accountMapper.updateStatus(account.getId(),INFORMATION_PERFECT);
        map.put("userMessage","添加成功");
        return map;
    }

}
