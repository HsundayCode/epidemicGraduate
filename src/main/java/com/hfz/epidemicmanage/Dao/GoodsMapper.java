package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    int insertGoods(Goods goods);
    Goods selectGoodsById(int id);
    List<Goods> selectGoodsByName(String name,int limit,int offset);
    List<Goods> selectGoodsBySource(String source, int limit,int offset);
    List<Goods> selectGoodsByPlace(@Param("place") String place,@Param("limit") int limit, @Param("offset") int offset);
    List<Goods> selectGoodsByStatus(@Param("status") int status,@Param("limit") int limit, @Param("offset") int offset);
    List<Goods> selectGoodsAll(@Param("limit")int limit,@Param("offset")int offset);
    int updateStatus(int id,int status);
    int deleteGoods(int id);
    int updatePlace(int id,String place,String Modifier,int status);
    int updateSource(int id,String source,String Modifier);

}
