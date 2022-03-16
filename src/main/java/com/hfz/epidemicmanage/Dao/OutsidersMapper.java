package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Outsider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OutsidersMapper {

    List<Outsider> selectOutsiderByName(String name, int limit, int offset);

    List<Outsider> selectOutsiderBySource(String source, int limit, int offset);

    List<Outsider> selectOutsiderByIdcard(int idcard, int limit, int offset);
    List<Outsider> selectOutsiderAll(int limit ,int offset);
    Outsider selectOutsiderByAccountid(int accountid);

    int insertOutsider(Outsider outsider);
    Outsider selectById(int id);
    int deleteOutsider(int id);
    int updateStatus(int id,int status);
}
