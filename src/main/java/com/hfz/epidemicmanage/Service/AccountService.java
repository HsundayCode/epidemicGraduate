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
import java.util.List;
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


    //通过账号id(主键)查询账号实体
    public Account FindAccountById(int id)
    {
        return accountMapper.selectById(id);
    }
    //通过注册名字(登陆账号)
    public Account FindAccountByName(String name)
    {
        return accountMapper.selectByName(name);
    }
    //通过注册邮箱查询账号
    public Account FindAccountByEmail(String email)
    {
        return accountMapper.selectByEmail(email);
    }

    //注册账号
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
        //查询数据库
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
        account.setPassword(EpidemicUtil.getmd5(account.getPassword() + account.getSalt()));//密码md5加密
        account.setType(0);//设置权限
        account.setStatus(0);//设置状态
        account.setCreatetime(new Date());//注册时间
        account.setActivationcode(EpidemicUtil.generateUUID()); //设置激活码
       // account.setEmail(account.getEmail());
        //accountMapper.insertAccount(account);//应该先放进缓存设置过期时间，激活时再放进数据库
        //发送激活邮件
        String ActivationcodeKey = RedisKeyUtil.getActivationcodeKey(account.getActivationcode());//获得激活码的kye
        redisTemplate.opsForValue().set(ActivationcodeKey,account);//加入缓存
        redisTemplate.expire(ActivationcodeKey,5*60,TimeUnit.SECONDS);//5分钟过期

        Context context = new Context();//模板
        context.setVariable("email",account.getEmail());//设置模板属性
        //注册接口/+姓名(账号)+账号激活码  xxx
        //不能用中文做url
        String url ="http://localhost:8080/register/"+account.getActivationcode();
        context.setVariable("url",url);//设置模板属性
        String content = templateEngine.process("mail/registmail",context);

        mailClient.sendMail(account.getEmail(),"激活账号",content);

        return map;
    }

    //激活判断 会改成redis
    //传入从url里获得的激活码
    public int activation(String Activationcode){
        //Account account = accountMapper.selectById(id);//
        //String code = account.getActivationcode();
        String ActivationcodeKey = RedisKeyUtil.getActivationcodeKey(Activationcode);//获得激活码再redis里的key
        Account account = (Account) redisTemplate.opsForValue().get(ActivationcodeKey);//获得account实体
        Account accountDB = accountMapper.selectByName(account.getName());
        //注册出了点问题，多次点击激活会数据库会有多个相同账号，因为缓存里的状态码没变，判断会出现问题，激活后要删除缓存，
        if(account == null || accountDB.getStatus() == 1)//是否已激活，判断是否已激活要考虑到数据库是否已存在
        {
            return ACTIVATION_REPEAT;
        }else if(Activationcode.equals(account.getActivationcode()))//激活码是否一致
        {
            account.setStatus(1);//设置激活状态
            accountMapper.insertAccount(account);//添加进数据库
            redisTemplate.delete(ActivationcodeKey);//删除缓存
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }

    //登录 判断该账号合理性
    public Map<String,Object> login(String username,String password,long expire){
        Map<String,Object> map = new HashMap<>();//返回给前端的容器
        Account accountDB = accountMapper.selectByName(username);//通过账号(name)在数据查找账号
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
        if(accountDB.getStatus() == 2)
        {
            map.put("accountMessage","账号还未激活");
            return map;
        }

       password = EpidemicUtil.getmd5(password+ accountDB.getSalt());//加密后比较
        if(!password.equals(accountDB.getPassword()))
        {
            map.put("passwordMessage","密码错误");
            return map;
        }

        //创建登录凭证 作为拦截器判断cookie
        //每次登录好像都会创建一个新的ticket
        //可以先再缓存判断再新建
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setId(accountDB.getId());//账号的id
        loginTicket.setUsername(accountDB.getName());//账号用户名
        loginTicket.setTicket(EpidemicUtil.generateUUID());//登录凭证唯一码
        loginTicket.setStatus(0);//设置账号登录状态
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expire * 1000));//过期时间，放进cookie

        String ticketKey = RedisKeyUtil.getTicketKey(loginTicket.getTicket());
        //拦截器用但现在感觉这个时多此一举了
        redisTemplate.opsForValue().set(ticketKey,loginTicket);//里面有用户id
        redisTemplate.expire(ticketKey,expire * 1000,TimeUnit.SECONDS);//过期时间

        map.put("ticket",loginTicket.getTicket());
        return map;

    }

    //登出
    public void logout(HttpServletRequest request){
        String ticket = CookieUtil.getCookie(request,"ticket"); //cookie取出ticketKey
        String ticketKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket=(LoginTicket) redisTemplate.opsForValue().get(ticketKey);//再从redis中取出loginTicket 删除
        loginTicket.setStatus(1);//设置账号登录状态
        redisTemplate.opsForValue().set(ticketKey,loginTicket);

    }

    public List<Account> findManageAccount()
    {
        return accountMapper.selectManage();
    }

    public List<Account> findNormalAccount()
    {
        return accountMapper.selectNormal();
    }

    public void changeType(int type,int id)
    {
        accountMapper.updateType(type,id);
    }

}
