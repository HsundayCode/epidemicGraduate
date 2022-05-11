package com.hfz.epidemicmanage.Dao;

import com.hfz.epidemicmanage.Entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper {
    int insertPost(Post post);//添加
    List<Post> selectPostList(@Param("limit") int limit, @Param("offset") int offset);//查询全部
    Post selectPostById(int postid);//帖子详情
    int updateStatus(@Param("postid") int postid,@Param("status") int status);//更新状态

    List<Post> selectUnResolvedList(@Param("limit") int limit, @Param("offset") int offset);
    List<Post> selectResolvedList(@Param("limit") int limit, @Param("offset") int offset);

    List<Post> selectPostByName(String name,int limit,int offset);

    int deletePost(int id);
}
