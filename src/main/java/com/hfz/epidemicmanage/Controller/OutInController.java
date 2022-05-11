package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.OutInMapper;
import com.hfz.epidemicmanage.Entity.Flow;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Service.OutInService;
import com.hfz.epidemicmanage.Util.ErweimaUtil;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import com.hfz.epidemicmanage.annotation.ManageRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class OutInController {

    @Autowired
    OutInService outInService;
    @Autowired
    OutInMapper outInMapper;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    ErweimaUtil erweimaUtil;

    @Value("${epidemicmanage.idlocal}")
    private String local;

    @Value("${epidemicmanage.tupianpath}")
    private String tupianpath;

    @RequestMapping(path = "/setewmPage",method = RequestMethod.GET)
    public String setewmpage(){
        return "views/createErweima";
    }

    @RequestMapping(path = "/getewmPage",method = RequestMethod.GET)
    public String getewmpage(){
        return "detailTemplate/getErweima";
    }


    //设置二维码，生成
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/seterweima",method = RequestMethod.POST)
    @ResponseBody
    public String setErweima(String place, HttpServletResponse response)throws Exception{
        if((outInMapper.selectErweimacode(place)) != null)//证明数据库里有数据
        {
            return "重复设置，可直接获取二维码";
        }
        int ewmcode = new Random().nextInt(1000);//还要判断是否重复代码
        if(outInMapper.selectcode(ewmcode) != null)//证明数据库里有数据
        {
            return "设置失败，重新设置";
        }
        outInService.setewmcode(place,ewmcode,tupianpath+ewmcode+".jpg");
        //需要判断是否已存在
        erweimaUtil.createErweima(ewmcode);
        return "设置成功";
    }

    //获取二维码
    @ManageRequire
    @LoginRequire
    @ResponseBody
    @RequestMapping(path = "/geterweima/{place}",method = RequestMethod.GET)
    public void getErweima(@PathVariable("place") String place, HttpServletResponse response)throws Exception{
        if(outInMapper.selectErweimacode(place) == null)
        {
            return;
        }
        int ewmcode = outInMapper.selectErweimacode(place);//地点代码
        //输出二维码
        FileInputStream inputStream = new FileInputStream(tupianpath+ewmcode+".jpg");
        response.setContentType("image/jpg");
        OutputStream os = response.getOutputStream();
        byte[] imgbuff = new byte[1024];
        int len=0;
        while((len=inputStream.read(imgbuff)) != -1)
        {
            os.write(imgbuff,0,len);
        }
        inputStream.close();
        os.close();
        return;
    }

    //扫码登记页面
    @RequestMapping(path = "/daka/{code}",method = RequestMethod.GET)
    public String dakajilu1(@PathVariable("code") int code,HttpServletResponse response) throws Exception{
        String place = outInMapper.selectcode(code);
        Context context = new Context();
        context.setVariable("place",place);
        String url = templateEngine.process("jilu",context);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(url);
        return null;
    }

    @RequestMapping(path = "/addjilu",method = RequestMethod.POST)
    @ResponseBody
    public String jilu(Flow flow)
    {
        flow.setRecordTime(new Date());
        outInService.addFlow(flow);
        return ""+flow.getName()+"登记成功";
    }

    @RequestMapping(path = "/getflowall",method = RequestMethod.GET)
    public String getFlowAll(Model model, Page page)
    {
        page.setLimit(5);
        page.setPath("/getflowall");
        List<Flow> flowall = outInService.findFlowList(page.getoffset(),page.getLimit());
        model.addAttribute("flowList",flowall);
        return "views/outin";
    }

    @RequestMapping(path = "/getflowbyplace/{place}",method = RequestMethod.GET)
    public String getFlowByPlace(@PathVariable("place") String place,Page page,Model model)
    {
        page.setLimit(5);
        page.setPath("/getflowbyplace/"+place);
        List<Flow> flowByPlace = outInService.findFlowByPlace(place,page.getLimit(),page.getoffset());
        model.addAttribute("flowList",flowByPlace);
        return "views/outin";
    }

    @RequestMapping(path = "/getflowbyname/{name}",method = RequestMethod.GET)
    public String getFlowByName(@PathVariable("name") String name,Page page,Model model)
    {
        page.setLimit(5);
        page.setPath("/getflowbyname/"+name);
        List<Flow> flowByNmae = outInService.findFlowByName(name,page.getLimit(),page.getoffset());
        model.addAttribute("flowList",flowByNmae);
        return "views/outin";
    }

}
