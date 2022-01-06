package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.LoginTicket;
import com.hfz.epidemicmanage.Util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.ConcurrentDateFormat;
import org.apache.tomcat.util.http.parser.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService implements EpidemicConstant {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    MailClient mailClient;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HostHolder hostHolder;


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
        account.setPassword(EpidemicUtil.getmd5(account.getPassword() + account.getSalt()));
        account.setType(0);
        account.setStatus(0);
        account.setCreatetime(new Date());
        account.setActivationcode(EpidemicUtil.generateUUID()); //设置激活码
       // account.setEmail(account.getEmail());
        accountMapper.insertAccount(account);
        //发送激活邮件

        Context context = new Context();
        context.setVariable("email",account.getEmail());
        String url ="http://localhost:8080/register/"+accountMapper.selectByName(account.getName()).getId()+"/"+account.getActivationcode();
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

    public Map<String,Object> login(String username,String password,long expire){
        Map<String,Object> map = new HashMap<>();
        Account accountDB = accountMapper.selectByName(username);
        if(StringUtils.isBlank(username))
        {
            map.put("accountMessage","账号不能为空");
            return map;
        }
        if(StringUtils.isBlank(password))
        {
            map.put("passwordMessage","密码不能为空");
            return map;
        }
        if(accountDB == null)
        {
            map.put("accountMessage","账号不存在");
            return map;
        }
        if(accountDB.getStatus() != 1)
        {
            map.put("accountMessage","账号还未激活");
            return map;
        }

       password = EpidemicUtil.getmd5(password+ accountDB.getSalt());
        if(!password.equals(accountDB.getPassword()))
        {
            map.put("passwordMessage","密码错误");
            return map;
        }

        //创建登录凭证 作为拦截器判断cookie
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setId(accountDB.getId());
        loginTicket.setUsername(accountDB.getName());
        loginTicket.setTicket(EpidemicUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expire * 1000));

        String ticketKey = RedisKeyUtil.getTicketKey(loginTicket.getTicket());

        redisTemplate.opsForValue().set(ticketKey,loginTicket);//里面有用户id
        redisTemplate.expire(ticketKey,expire * 1000,TimeUnit.SECONDS);//过期时间

        map.put("ticket",loginTicket.getTicket());
        return map;

    }

    //cookie取出ticketKey再从redis中取出loginTicket 删除
    public void logout(HttpServletRequest request){
        String ticket = CookieUtil.getCookie(request,"ticket");
        String ticketKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket=(LoginTicket) redisTemplate.opsForValue().get(ticketKey);
        loginTicket.setStatus(1);

    }

}
