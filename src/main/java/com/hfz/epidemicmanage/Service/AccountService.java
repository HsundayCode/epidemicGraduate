package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
import com.hfz.epidemicmanage.Util.EpidemicUtil;
import com.hfz.epidemicmanage.Util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService implements EpidemicConstant {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    MailClient mailClient;
    @Autowired
    TemplateEngine templateEngine;


    public Account FindAccountById(int id)
    {
        return accountMapper.selectById(id);
    }

    public Account FindAccountByNae(String name)
    {
        return accountMapper.selectByName(name);
    }

    public Account FindAccountByEmail(String email)
    {
        return accountMapper.selectByEmail(email);
    }

    public Map<String,Object>  addAccount(Account account)
    {
        Map<String,Object> map = new HashMap<>();
        //判断注册账号是否合理
        if(account == null)
        {
            throw new IllegalArgumentException("不能为空");
        }
        if(StringUtils.isBlank(account.getName()))
        {
            map.put("nameMessage","账号不能为空");
            return map;
        }
        if(StringUtils.isBlank(account.getPassword()))
        {
            map.put("passwordMessage","密码不能为空");
            return map;
        }
        if(StringUtils.isBlank(account.getEmail()))
        {
            map.put("emailMessage","邮箱不能为空");
            return map;
        }
        Account accountDB = accountMapper.selectByName(account.getName());
        if(accountDB != null)
        {
            map.put("nameMessage","账号已存在");
            return map;
        }
        accountDB = accountMapper.selectByEmail(account.getEmail());
        if(accountDB != null)
        {
            map.put("emailMessage","邮箱已存在");
            return map;
        }
        //对注册进行加工，在存入数据库
        account.setSalt(EpidemicUtil.generateUUID().substring(0,5));//唯一码
        account.setPassword(EpidemicUtil.getmd5(account.getPassword()) + account.getSalt());
        account.setType(0);
        account.setStatus(0);
        account.setCreatetime(new Date());
        account.setActivationcode(EpidemicUtil.generateUUID()); //设置激活码
        accountMapper.insertAccount(account);
        //发送激活邮件

        Context context = new Context();
        context.setVariable("email",account.getEmail());
        String url ="http://localhost:8080/account/register/"+accountMapper.selectByName(account.getName()).getId()+"/"+account.getActivationcode();
        context.setVariable("url",url);
        String content = templateEngine.process("mail/registmail",context);
        mailClient.sendMail(account.getEmail(),"激活账号",content);

        return map;
    }

    public int activation(int id,String key){
        Account account = accountMapper.selectById(id);
        String code = account.getActivationcode();
        if(account.getStatus() == 1)
        {
            return ACTIVATION_REPEAT;
        }else if(key.equals(code))
        {
            accountMapper.updateStatus(id,1);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }


}
