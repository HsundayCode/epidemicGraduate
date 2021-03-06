package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.UserService;
import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import com.hfz.epidemicmanage.annotation.ManageRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(path = "/getdetailpage",method = RequestMethod.GET)
    public String getdetailpage(){
        return "detailTemplate/userdetail";
    }

    //用户添加信息
    //需要判断用户是否已经存在信息，不能重复提交，判断账号id是否在user里存在
    //添加完信息后应该返回首页
    @LoginRequire
    @RequestMapping(path = "/information",method = RequestMethod.POST)
    public String addinformation(Model model, User user){
        user.setStatus("正常");
        Map<String,Object>  map = userService.addUser(user);
        model.addAttribute("userMessage",map.get("userMessage"));
        return "redirect:/index";
    }

    //删除
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/deleteuser/{userid}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userid") int id, Model model)
    {
        userService.deleteUser(id);
        List<Map<String,Object>> afmaplist = userService.findUsers(5,0);
        model.addAttribute("maplist",afmaplist);
        return "views/users :: userrsp";
    }

    //修改用户状态
    @LoginRequire
    @ResponseBody
    @RequestMapping(path = "/updateStatus",method = RequestMethod.POST)
    public String updateStatus( int userid,String status)
    {
        userService.updateStatus(userid,status);
        return "修改成功";
    }

    //通关名字查找
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/getByName/{name}",method = RequestMethod.GET)
    public String getUserByName(Model model, @PathVariable("name") String name, Page page)
    {
        List<Map<String,Object>> maplist = userService.findUserByName(name);
        model.addAttribute("maplist",maplist);
        return "views/users";//返回全部列表
    }

    //通关身份证查找
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/getByIdcard/{idcard}",method = RequestMethod.GET)
    public String getUserByIdcard(Model model,@PathVariable("idcard") String idcard,Page page)
    {

        List<Map<String,Object>> maplist = userService.findUserByIdcard(idcard);

        model.addAttribute("maplist",maplist);
        return "views/users";//返回全部列表
    }


    //获得全体列表
    @LoginRequire
    @ManageRequire
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
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/Userdetail/{Userid}",method = RequestMethod.GET)
    public String getPatientDetail(Model model, @PathVariable("Userid") int userid){
        User user= userService.findUserById(userid);
        if (user == null)
        {
            model.addAttribute("res","用户不存在");
            return "addresult";
        }
        model.addAttribute("user",user);
        return "detailTemplate/userdetail";//返回一个用户实体类
    }

    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/updatedetail",method = RequestMethod.POST)
    @ResponseBody
    public String updatedetail(int id,String status,String place,String divide,String trail,String occurrencetime){
        userService.updateUser(id,status,place,divide,trail,occurrencetime);
        return "修改成功";
    }

}
