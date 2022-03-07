package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.UserService;
import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserMapper userMapper;

    @LoginRequire
    @RequestMapping(path = "/addinformation",method = RequestMethod.GET)
    public String getInformation(){
        return "addTemplate/addUser";
    }

    //用户添加信息
    //需要判断用户是否已经存在信息，不能重复提交，判断账号id是否在user里存在
    //添加完信息后应该返回首页
    @LoginRequire
    @RequestMapping(path = "/information",method = RequestMethod.POST)
    public String addinformation(Model model, User user){
        Map<String,Object> map = new HashMap<>();
        Account account = hostHolder.getAccount();
        if(account.getStatus() == 3)
        {
            map.put("userMessage","信息重复添加");
            model.addAttribute("res",map.get("userMessage"));
            return "/addresult";
        }
       map = userService.addUser(user);
        //判断map是否为空，返回指定页面
        if(map != null)
        {
            model.addAttribute("userMessage",map.get("userMessage"));
            return "/userdetail";
        }
       return "index";
    }

    //删除
    @RequestMapping(path = "/deleteuser/{userid}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userid") int id, Model model)
    {
        userService.deleteUser(id);
        model.addAttribute("res","删除成功");
        return "/addresult";
    }

    //修改用户状态
    @LoginRequire
    @RequestMapping(path = "/updateStatus/{accountid}",method = RequestMethod.GET)
    public void updateStatus(@PathVariable("accountid") int accountid,String status)
    {
        userService.updateStatus(accountid,status);
    }

    //通关名字查找
    @RequestMapping(path = "/getByName/{name}",method = RequestMethod.GET)
    public String getUserByName(Model model, @PathVariable("name") String name, Page page)
    {
        List<Map<String,Object>> maplist = userService.findUserByName(name);
        model.addAttribute("maplist",maplist);
        return "views/users";//返回全部列表
    }

    //通关身份证查找
    @RequestMapping(path = "/getByIdcard/{idcard}",method = RequestMethod.GET)
    public String getUserByIdcard(Model model,@PathVariable("idcard") int idcard,Page page)
    {

        List<Map<String,Object>> maplist = userService.findUserByIdcard(idcard);
        model.addAttribute("maplist",maplist);
        return "views/users";//返回全部列表
    }

    //因为分页的原因条件查询要分开不能用post来分页？
    //获得全体列表
    @LoginRequire
    @RequestMapping(path = "/UserList",method = RequestMethod.GET)
    public String getUsersList(Model model, Page page){
        page.setLimit(5);
        page.setPath("/UserList");
        List<Map<String,Object>> maplist = userService.findUsers(page.getLimit(),page.getoffset());
        model.addAttribute("maplist",maplist);
        return "views/users";//返回全部列表
    }

    //根据id获得用户详情
    //用户列表中会有用户id
    @LoginRequire
    @RequestMapping(path = "/Userdetail/{Userid}",method = RequestMethod.GET)
    public String getPatientDetail(Model model, @PathVariable("Userid") int patientid){
        User user= userService.findUserById(patientid);
        model.addAttribute("user",user);
        return "/patientdetail";//返回一个用户实体类
    }

}
