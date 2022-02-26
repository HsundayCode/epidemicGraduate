package com.hfz.epidemicmanage.Service;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Dao.PostMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.Post;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
import com.hfz.epidemicmanage.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService implements EpidemicConstant {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;

    //添加反馈信息
    public Map<String,Object> addPost(String title,String content){
        Map<String,Object> map = new HashMap<>();
        Post post = new Post();
        Account account = hostHolder.getAccount();
        User user = userMapper.selectById(account.getId());

        post.setAccountid(account.getId());//账号id，通过账号id可以知道用户信息
        post.setContent(content);//内容
        post.setTitle(title);//标题
        post.setCreateTime(new Date());//创建时间
        post.setStatus(POST_UNREAD);//消息状态

        postMapper.insertPost(post);
        return map;
    }

    //根据id可以查看帖子详情
    //单个用户的全部消息反馈信息
    public Post FindPostDetail(int postid){
        Post post = postMapper.selectPostById(postid);
        return post;
    }

    //获取全部反馈信息
    public List<Map<String,Object>> getPostList(int limit,int offset)
    {
        List<Post> postList = postMapper.selectPostList(limit,offset);
        List<Map<String,Object>> postvo = new ArrayList<>();
        for(Post post : postList)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("user",userMapper.selectByAccountid(post.getAccountid()));
            map.put("post",post);
            postvo.add(map);
        }
        return postvo;
    }

    public void updateStatus(int posttid,int status)
    {
        if(status == POST_UNREAD)
        postMapper.updateStatus(posttid,POST_READ);
    }

    //未解决反馈信息列表
    public List<Map<String,Object>> getUnResolvedList(int limit,int offset)
    {

        List<Post> UnResolvedList = postMapper.selectUnResolvedList(limit,offset);
        List<Map<String,Object>> unresolved = new ArrayList<>();
        for(Post post : UnResolvedList)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("user",userMapper.selectByAccountid(post.getAccountid()));
            map.put("post",post);
            unresolved.add(map);
        }
        return unresolved;
    }
    //已解决反馈信息列表
    public List<Map<String,Object>> getResolvedList(int limit,int offset)
    {

        List<Post> ResolvedList = postMapper.selectResolvedList(limit,offset);
        List<Map<String,Object>> resolved = new ArrayList<>();
        for(Post post : ResolvedList)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("user",userMapper.selectByAccountid(post.getAccountid()));
            map.put("post",post);
            resolved.add(map);
        }
        return resolved;
    }
}
