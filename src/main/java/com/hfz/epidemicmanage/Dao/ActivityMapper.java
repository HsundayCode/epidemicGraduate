package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {

    List<Activity> selectActivityByStatus(int status,int limit,int offset);

    List<Activity> selectActivityByActime(String actime,int limit,int offset);

    List<Activity> selectActivityByPlace(String place,int limit,int offset);

    Activity selectActivityByTitle(String title);
    Activity selectActivityById(int id);

    List<Activity> selectAll(@Param("limit") int limit,@Param("offset") int offset);

    int insertActivity(Activity activity);
    int deleteActivity(int id);
    int updateStatus(@Param("status") int status,@Param("id") int id);
}
