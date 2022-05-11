package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Goods;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Service.GoodsService;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
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

import java.util.List;

@Controller
public class GoodsController implements EpidemicConstant {
    @Autowired
    GoodsService goodsService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserMapper userMapper;

    @RequestMapping(path = "/addGoods",method = RequestMethod.GET)
    public String getAddGoodsPage(){
        return "addTemplate/addGoods";
    }

    //添加物资
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/addGoods",method = RequestMethod.POST)
    public String addGoods(Model model, String name,String source,String place)
    {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setSource(source);
        goods.setPlace(place);
        goods.setStatus(GOODS_UNUSE);
        goods.setAdder(userMapper.selectByAccountid(hostHolder.getAccount().getId()).getName());
        goodsService.addGoods(goods);
        model.addAttribute("res","添加成功");
        return "addresult";
    }

    //获得所有物资列表
    @LoginRequire
    @ManageRequire
    @RequestMapping(path = "/getGoodsAll",method = RequestMethod.GET)
    public String getGoodsAll(Model model,Page page)
    {
        page.setPath("/getGoodsAll");
        page.setLimit(5);
        List<Goods> goodsAll = goodsService.findGoodsAll(page.getLimit(),page.getoffset());
        model.addAttribute("goodsList",goodsAll);
        return "views/goods";
    }

    //根据名称查询获得列表
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/getGoodsNameList/{name}",method = RequestMethod.GET)
    public String getGoodsByName(Model model,Page page,@PathVariable("name") String name){
        List<Goods> goodsList = goodsService.findGoodsByName(name,page.getLimit(),page.getoffset());
        model.addAttribute("goodsList",goodsList);
        return "views/goods";
    }


    //根据来源查询获得物资列表
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/getGoodsSourceList/{source}",method = RequestMethod.GET)
    public String getGoodsBySource(Model model,Page page,@PathVariable("source") String source){
        List<Goods> goodsList = goodsService.findGoodsBySource(source,page.getLimit(),page.getoffset());
        model.addAttribute("goodsList",goodsList);
        return "views/goods";
    }

    //根据去处查询获得物资列表
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/getGoodPlaceList/{place}",method = RequestMethod.GET)
    public String getGoodsByPlace(Model model,Page page,@PathVariable("place") String place){
        List<Goods> goodsList = goodsService.findGoodsByPlace(place,page.getLimit(),page.getoffset());
        model.addAttribute("goodsList",goodsList);
        return "views/goods";
    }

    //根据状态查询获得物资列表
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/getGoodsStatusList/{status}",method = RequestMethod.GET)
    public String getGoodsByStatus(Model model,Page page,@PathVariable("status") int status){
        List<Goods> goodsList = goodsService.findGoodsByStatus(status,page.getLimit(),page.getoffset());
        model.addAttribute("goodsList",goodsList);
        return "views/goods";
    }

    //物品详情
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/getGoodsDetail/{id}",method = RequestMethod.GET)
    public String getGoodsDetail(Model model,@PathVariable("id") int goodsid)
    {
        Goods goods = goodsService.findGoodsDetail(goodsid);
        model.addAttribute("goods",goods);
        return "detailTemplate/goodsdetail";
    }

    //删除
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/deletegoods/{goodsid}",method = RequestMethod.GET)
    public String deleteGoods(@PathVariable("goodsid") int id,Model model)
    {
        goodsService.deleteGoods(id);
        List<Goods> afgoodsAll = goodsService.findGoodsAll(5,0);
        model.addAttribute("goodsList",afgoodsAll);
        return "views/goods :: goodsrep";
    }

    //修改用途
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/changePlace",method = RequestMethod.POST)
    @ResponseBody
    public String updatePlace(Model model,String place,int id)
    {
        System.out.println(place);
        String Modifier = userMapper.selectByAccountid(hostHolder.getAccount().getId()).getName();
        goodsService.updatePlace(id,place,Modifier);
        return "修改成功";
    }

    //修改来源
    @ManageRequire
    @LoginRequire
    @RequestMapping(path = "/changeSource/{id}/{source}",method = RequestMethod.GET)
    @ResponseBody
    public String updateSource(@PathVariable("source") String source,@PathVariable("id")int id)
    {
        String Modifier = userMapper.selectByAccountid(hostHolder.getAccount().getId()).getName();
        goodsService.updateStatus(id,1);
        goodsService.updateSource(id,source,Modifier);
        return "修改成功";
    }

//    //修改状态
//    @RequestMapping(path = "/changeStatus/{id}",method = RequestMethod.GET)
//    public String updateStatus(@PathVariable("id")int id){
//        goodsService.updateStatus(id,1);
//        return "redirect:/getGoodsAll";
//    }
}
