package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Entity.Patient;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.PatientService;
import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    HostHolder hostHolder;

//    //获得用户详情页面
//    @RequestMapping(path = "/change",method = RequestMethod.GET)
//    public String getAddPage(){return "/patientdetail"; }

    //获得用户列表页面
//    @LoginRequire
//    @RequestMapping(path = "/patientList",method = RequestMethod.GET)
//    public String getPatientListPage(){
//        return "/patients";
//    }

    //添加病人信息
    @LoginRequire
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public String addPatient(Model model,int userid,String status,String place,String divide,String trail,String occurrencetime){
        Account account = hostHolder.getAccount();
        if(account == null)
        {
            model.addAttribute("403","还没有登录");
            return "/index";
        }
        patientService.updatePatient(userid,status,place,divide,trail,occurrencetime);
        model.addAttribute("patientMessage","修改成功");
        return "/patientdetail";
    }
    //因为分页的原因条件查询要分开不能用post来分页？
    @LoginRequire
    @RequestMapping(path = "/patientList",method = RequestMethod.GET)
    public String getPatientsList(Model model, Page page){
        page.setLimit(5);
        page.setPath("/patientList");
        //List<Map<String,Object>> patients = new ArrayList<>();
            //返回的map里有List<Map<String,Object>>
            Map<String,Object> map = patientService.findPatients(page.getLimit(),page.getoffset());
            model.addAttribute("patientList",map.get("patientList"));
            return "/patients";//返回全部列表


    }

    //根据id获得用户详情
    //用户列表中会有用户id
    @LoginRequire
    @RequestMapping(path = "/patientdetail/{patientid}",method = RequestMethod.GET)
    public String getPatientDetail(Model model, @PathVariable("patientid") int patientid){
        User patient= patientService.findPatientById(patientid);
        model.addAttribute("Patient",patient);
        return "/patientdetail";//返回一个用户实体类
    }

    //修改用户状态
    @LoginRequire
    @RequestMapping(path = "/updateStatus/{accountid}",method = RequestMethod.GET)
    public void updateStatus(@PathVariable("accountid") int accountid,String status)
    {
        patientService.updateStatus(accountid,status);
    }

}
