package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.GoodsMapper;
import com.hfz.epidemicmanage.Entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public void addGoods(Goods goods){
        goodsMapper.insertGoods(goods);
    }

    public List<Goods> findGoodsByName(String name,int limit,int offset){
        List<Goods> goodsNameList = goodsMapper.selectGoodsByName(name,limit,offset);
        return goodsNameList;
    }

    public List<Goods> findGoodsBySource(String source,int limit,int offset)
    {
        List<Goods> goodsFromList = goodsMapper.selectGoodsBySource(source,limit,offset);
        return goodsFromList;
    }

    public List<Goods> findGoodsByPlace(String place,int limit,int offset){
        List<Goods> goodsToList = goodsMapper.selectGoodsByPlace(place,limit,offset);
        return goodsToList;
    }

    public List<Goods> findGoodsByStatus(int status,int limit,int offset){
        List<Goods> goodsStatusList = goodsMapper.selectGoodsByStatus(status,limit,offset);
        return goodsStatusList;
    }

    public List<Goods> findGoodsAll(int limit,int offset){
        return goodsMapper.selectGoodsAll(limit,offset);
    }

    public void updateStatus(int id,int status)
    {
        goodsMapper.updateStatus(id,status);
    }

    public void deleteGoods(int id)
    {
        goodsMapper.deleteGoods(id);
    }
}