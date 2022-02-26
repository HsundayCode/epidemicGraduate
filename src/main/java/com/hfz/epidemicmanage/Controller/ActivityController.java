package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Entity.Activity;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Service.ActivityService;
import com.hfz.epidemicmanage.Util.GetJSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @RequestMapping(path = "/addActivity",method = RequestMethod.GET)
    public String getaddPage(){
        return "addTemplate/addActivity";
    }

    //根据获得id查询，用来查询获得活动详情
    @ResponseBody
    @RequestMapping(path = "/getAcById/{id}",method = RequestMethod.GET)
    public String getActivityListId(Model model, @PathVariable("id") int id){
        Activity ByIdAc = activityService.findActivityById(id);

        model.addAttribute("AcList",ByIdAc);
        return GetJSONUtil.toJSON(0,ByIdAc.getContent());

    }

    //根据活动状态查询获得活动列表，
    @RequestMapping(path = "/getAcByStatus/{status}",method = RequestMethod.GET)
    public String getActivityListStatus(Model model, @PathVariable("status") int status, Page page){
        page.setPath("/getAcByStatus/"+status);
        page.setLimit(5);
        List<Activity> ByStatusList = activityService.findActivityByStatus(status,page.getLimit(),page.getoffset());
        model.addAttribute("AcList",ByStatusList);
        return "views/Activity";
    }

    //根据活动地点查询获得活动列表
    @RequestMapping(path = "/getAcByPlace/{place}",method = RequestMethod.GET)
    public String getActivityListPlace(Model model,@PathVariable("place") String place, Page page){
        page.setLimit(5);
        page.setPath("/getAcByPlace/"+place);
        List<Activity> ByPlaceList = activityService.findActivityByPlace(place,page.getLimit(),page.getoffset());
        model.addAttribute("AcList",ByPlaceList);
        return "views/Activity";
    }

    //根据获得时间查询获得活动列表
    @RequestMapping(path = "/getAcByActime/{actime}",method = RequestMethod.GET)
    public String getActivityListActime(Model model,@PathVariable("actime") String actime, Page page){
        page.setLimit(5);
        page.setPath("/getAcByActime/"+actime);
        List<Activity> ByActimeList = activityService.findActivityByActime(actime,page.getLimit(),page.getoffset());
        model.addAttribute("AcList",ByActimeList);
        return "views/Activity";
    }

    //根据获得标题查询获得活动列表
    @RequestMapping(path = "/getAcByTitle/{title}",method = RequestMethod.GET)
    public String getActivityListTitle(Model model, @PathVariable("title") String title, Page page){
        page.setLimit(5);
        page.setPath("/getAcByTitle/"+title);
        Activity ByTitleList = activityService.findActivityByTitle(title);
        model.addAttribute("AcList",ByTitleList);
        return "views/Activity";
    }


    //查询所有活动列表
    @RequestMapping(path = "/getAcAll",method = RequestMethod.GET)
    public String getActivityAllList(Model model,Page page){
        System.out.println(page.getCurrent());
        page.setPath("/getAcAll");
        page.setLimit(5);
        List<Activity> AcAllList = activityService.findActivityAll(page.getLimit(),page.getoffset());
        model.addAttribute("AcList",AcAllList);
        return "views/Activity";
    }

    //添加活动
    @RequestMapping(path = "/addActivity",method = RequestMethod.POST)
    public String addActivity(Model model,Activity activity)
    {
        System.out.println(activity.getActime());
        activityService.addActivity(activity);
        model.addAttribute("res","添加成功");
        return "addresult";
    }

    //删除
    @RequestMapping(path = "/deleteactivity/{acid}",method = RequestMethod.GET)
    public String deleteActivity(@PathVariable("acid") int id,Model model)
    {
        activityService.deleteActivity(id);
        model.addAttribute("res","删除成功");
        return "/addresult";
    }

    //修改未开始 开始 结束
    //传入当前的status，后台判断修改

    @RequestMapping(path = "/updateAcStatus/{acid}/{status}",method = RequestMethod.GET)
    public String updateAcStatus(@PathVariable("acid") int id,@PathVariable("status") int status)
    {
        activityService.updateAcStatus(status,id);
        return "redirect:/getAcById/"+id;
    }
}
