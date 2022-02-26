package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Outsider;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Service.OutsiderService;
import com.hfz.epidemicmanage.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class OutsiderController {
    @Autowired
    OutsiderService outsiderService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserMapper userMapper;

    @RequestMapping(path = "/addOutsider",method = RequestMethod.GET)
    public String getaddpage()
    {
        return "addTemplate/addOutsider";
    }

    //查询所有外来人员列表
    @RequestMapping(path = "/getOutsiderAll",method = RequestMethod.GET)
    public String OutsiderAll(Model model,Page page){
        List<Outsider> outsiderAll = outsiderService.findOutsiderAll(page.getLimit(),page.getoffset());
        model.addAttribute("outsiderList",outsiderAll);
        return "views/outsider";
    }

    //添加外来人员
    @RequestMapping(path = "/addOutsider",method = RequestMethod.POST)
    public String addOutsider(Outsider outsider,Model model){
        if(outsider == null)
            throw new RuntimeException("没有填写信息");
        int accountid = hostHolder.getAccount().getId();
        if(accountid == 1 || accountid == 3)
        {
            outsider.setAccountid(userMapper.selectByName(outsider.getName()).getId());
        }
        outsider.setAccountid(accountid);
        outsiderService.addOutsider(outsider);
        model.addAttribute("res","添加成功");
        return "/addresult";
    }

    //根据名字查询获得外来人员列表
    @RequestMapping(path = "/bynamegetoutsider/{name}",method = RequestMethod.GET)
    public String getOutsiderListByName(@PathVariable("name") String name, Page page, Model model){

        List<Outsider> outsidernameList = outsiderService.findOutsiderByName(name,page.getLimit(),page.getoffset());
        model.addAttribute("outsiderList",outsidernameList);
        return "views/outsider";
    }

    //根据来源查询获得外来人员列表
    @RequestMapping(path = "/bysourcegetoutsider/{source}",method = RequestMethod.GET)
    public String getOutsiderListBySource(@PathVariable("source") String source, Page page, Model model){

        List<Outsider> outsidersourceList = outsiderService.findOutsiderBySource(source,page.getLimit(),page.getoffset());
        model.addAttribute("outsiderList",outsidersourceList);
        return "views/outsider";
    }

    //根据身份证查询获得外来人员列表
    @RequestMapping(path = "/byidcardgetoutsider/{idcard}",method = RequestMethod.GET)
    public String getOutsiderListByIdcard(@PathVariable("idcard") int idcard, Model model,Page page){

        List<Outsider> outsideridcardList = outsiderService.findOutsiderByIdcard(idcard,page.getLimit(),page.getoffset());
        model.addAttribute("outsiderList",outsideridcardList);
        return "views/outsider";
    }

    //外来人员详情，来的原因不能在表单里展示
    @RequestMapping(path = "/getDetail/{id}")
    public String outsiderDetail(Model model, @PathVariable("id")int id){
       Outsider detail =  outsiderService.getDetail(id);
       model.addAttribute("outsiderDetail",detail);
        return "outsiderdetail";
    }

    @RequestMapping(path = "/deleteoutsider/{outsiderid}",method = RequestMethod.GET)
    public String deleteOutsider(@PathVariable("outsiderid") int id,Model model)
    {
        outsiderService.deleteOutsider(id);
        model.addAttribute("res","删除成功");
        return "/addresult";
    }


}
