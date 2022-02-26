package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.PatientMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Entity.Record;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.RecordService;
import com.hfz.epidemicmanage.Service.UserService;
import com.hfz.epidemicmanage.Util.HostHolder;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import com.hfz.epidemicmanage.annotation.ManageRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserMapper userMapper;

//    @RequestMapping(path = "/addRecord",method = RequestMethod.GET)
//    public String getaddRecordPage(){
//        return "/record";
//    }

    //今天打卡人数
    //后需用ajax把
    public String gettodayRecordNum(Model model){
        int num =  recordService.findTodayRecordRow(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        model.addAttribute("recordNum",num);
        return "/index";
    }
    //获取某人历史打卡记录
    @LoginRequire
    @ManageRequire
    @RequestMapping(path = "/HistoryRecord",method = RequestMethod.POST)
    public String getHistoryRecordList(Model model, Page page,int accountid){
        List<Record> recordList = recordService.findHistoryRecordList(accountid,page.getLimit(),0);
//        if(recordList == null)
//        {
//            return "无此人";
//        }
        model.addAttribute("RecordList",recordList);
        return "views/record";
    }

    //添加打卡记录
    @LoginRequire
    @ResponseBody
    @RequestMapping(path = "/addRecord",method = RequestMethod.POST)
    public String addRecord(Model model,String temperature,String locale)
    {
        int accountid = hostHolder.getAccount().getId();//当前添加信息的账号id
        Record record = new Record();
        //需要姓名和联系方式
        record.setTemperature(temperature);//体温
        record.setAccountid(accountid);
        record.setLocale(locale);//当前地址
        record.setCreatetime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前日期
        User user = userMapper.selectByAccountid(accountid);
        record.setName(user.getName());//名字
        record.setPhone(user.getPhone());//联系
        String result = recordService.addRecord(record);//
        //model.addAttribute("result",result);
        //return "/record";
        return result;
    }

    //最近打卡列表
    @LoginRequire
    @RequestMapping(path = "/getRecentlyRecordList",method = RequestMethod.GET)
    public String getRecentlyRecordList(Model model,Page page){
        List<Record> RecentlyRecordList = recordService.findRecentlyRecordList(page.getLimit(),0);
        model.addAttribute("RecordList",RecentlyRecordList);
        return "views/record";
    }

}
