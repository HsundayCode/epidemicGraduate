package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.OutInMapper;
import com.hfz.epidemicmanage.Entity.Flow;
import com.hfz.epidemicmanage.Service.OutInService;
import com.hfz.epidemicmanage.Util.ErweimaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.Random;

@Controller
public class OutInController {

    @Autowired
    OutInService outInService;
    @Autowired
    OutInMapper outInMapper;
    @Autowired
    TemplateEngine templateEngine;

    @RequestMapping(path = "/setewmPage",method = RequestMethod.GET)
    public String setewmpage(){
        return "views/createErweima";
    }

    @RequestMapping(path = "/getewmPage",method = RequestMethod.GET)
    public String getewmpage(){
        return "detailTemplate/getErweima";
    }


    //设置二维码，生成
    @RequestMapping(path = "/seterweima",method = RequestMethod.POST)
    @ResponseBody
    public String setErweima(String place, HttpServletResponse response)throws Exception{


        if((outInMapper.selectErweimacode(place)) != null)//证明数据库里有数据
        {
            return "重复设置，可直接获取二维码";
        }
          int ewmcode = new Random().nextInt(1000);//还要判断是否重复代码

        if( outInMapper.selectcode(ewmcode) != null)//证明数据库里有数据
        {
            return "获取失败，重新获取";
        }
        outInService.setewmcode(place,ewmcode);//设置地点代码，放在url
        //需要判断是否已存在
        ErweimaUtil.createErweima(ewmcode);
        return "设置成功";
    }

    //获取二维码
    @ResponseBody
    @RequestMapping(path = "/geterweima/{place}",method = RequestMethod.GET)
    public void getErweima(@PathVariable("place") String place, HttpServletResponse response)throws Exception{
        int ewmcode = outInMapper.selectErweimacode(place);//地点代码
        //输出二维码
        FileInputStream inputStream = new FileInputStream("F:/epidemicmanage/"+ewmcode+".jpg");
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
    }

    //扫码登记页面
    @RequestMapping(path = "/daka/{code}",method = RequestMethod.GET)
    public String dakajilu1(@PathVariable("code") int code,HttpServletResponse response) throws Exception{
        String place = outInMapper.selectcode(code);
        Context context = new Context();
        context.setVariable("place",place);
        String url = templateEngine.process("/jilu",context);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(url);
        return null;
    }

    @RequestMapping(path = "/addjilu",method = RequestMethod.POST)
    @ResponseBody
    public String jilu(Flow flow)
    {
        flow.setRecordTime(new Date());
        outInMapper.recoedFlow(flow);
        return ""+flow.getName()+"登记成功";
    }

}
