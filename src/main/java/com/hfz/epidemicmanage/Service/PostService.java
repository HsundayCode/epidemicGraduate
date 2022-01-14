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

    //添加
    public Map<String,Object> addPost(String title,String content){
        Map<String,Object> map = new HashMap<>();
        Post post = new Post();
        Account account = hostHolder.getAccount();
        User user = userMapper.selectById(account.getId());

        post.setAccountid(account.getId());
        post.setContent(content);
        post.setTitle(title);
        post.setCreateTime(new Date());
        post.setStatus(POST_UNRESOLVED);

        postMapper.insertPost(post);
        return map;
    }

    //根据id可以查看帖子详情
    public Post FindPostDetail(int postid){
        Post post = postMapper.selectPostById(postid);
        return post;
    }

    //获取全部
    public List<Map<String,Object>> getPostList(int limit,int offset)
    {
        List<Post> postList = postMapper.selectPostList(5,0);
        List<Map<String,Object>> postvo = new ArrayList<>();
        for(Post post : postList)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("account",accountMapper.selectById(post.getAccountid()));
            map.put("post",post);
            postvo.add(map);
        }
        return postvo;
    }

    public void updateStatus(int accountid,int status)
    {
        postMapper.updateStatus(accountid,status);
    }

    public List<Map<String,Object>> getUnResolvedList(String accountname,int limit,int offset)
    {
        int accountid = accountMapper.selectByName(accountname).getId();
        List<Post> UnResolvedList = postMapper.selectUnResolvedList(accountid,5,0);
        List<Map<String,Object>> unresolved = new ArrayList<>();
        for(Post post : UnResolvedList)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("account",accountMapper.selectById(post.getAccountid()));
            map.put("post",post);
            unresolved.add(map);
        }
        return unresolved;
    }
    public List<Map<String,Object>> getResolvedList(String accountname,int limit,int offset)
    {
        int accountid = accountMapper.selectByName(accountname).getId();
        List<Post> ResolvedList = postMapper.selectResolvedList(accountid,5,0);
        List<Map<String,Object>> resolved = new ArrayList<>();
        for(Post post : ResolvedList)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("account",accountMapper.selectById(post.getAccountid()));
            map.put("post",post);
            resolved.add(map);
        }
        return resolved;
    }
}
