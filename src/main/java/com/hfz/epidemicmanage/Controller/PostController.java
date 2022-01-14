package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Entity.Post;
import com.hfz.epidemicmanage.Entity.User;
import com.hfz.epidemicmanage.Service.PostService;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
import com.hfz.epidemicmanage.annotation.LoginRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class PostController implements EpidemicConstant {
    @Autowired
    PostService postService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountMapper accountMapper;


    @RequestMapping(path = "/add",method = RequestMethod.GET)
    public String getAddPostPage(){
        return "/post";
    }
    //提出问题
    @LoginRequire
    @RequestMapping(path = "/addPost",method = RequestMethod.POST)
    public String addPost(Model model,String title,String content){
        postService.addPost(title,content);
        return "/index";
    }

    //获取详细信息
    @LoginRequire
    @RequestMapping(path = "/postdetail/{postid}",method = RequestMethod.GET)
    public String PostDetail(Model model,@PathVariable("postid") int postid){
        Post postdetail = postService.FindPostDetail(postid);
        model.addAttribute("postdetail",postdetail);
        Account account = accountMapper.selectById(postdetail.getAccountid());
        model.addAttribute("account",account);
        return "/postdetail";
    }

    //修改状态
    @LoginRequire
    @RequestMapping(path = "/updatestatus/{postid}",method = RequestMethod.GET)
    public String updateStatus(Model model,@PathVariable("postid")int accountid){
        postService.updateStatus(accountid,POST_RESOLVED);
        return "redirect:/postdetail/"+accountid;
    }

    //获取全部列表
    @LoginRequire
    @RequestMapping(path = "/postList",method = RequestMethod.GET)
    public String getPostList(Model model,Page page){
        List<Map<String,Object>> postListMap =  postService.getPostList(page.getLimit(),page.getoffset());
        model.addAttribute("postListMap",postListMap);
        return "/postList";
    }



    //查询未解决
    @RequestMapping(path = "/unResolved",method = RequestMethod.POST)
    public String getUnResolvedList(Model model,String accountname,Page page){
        List<Map<String,Object>> postUnresolvedListMap = postService.getUnResolvedList(accountname,page.getLimit(),page.getoffset());
        model.addAttribute("postListMap",postUnresolvedListMap);
        return "/unresolved";
    }

    //查询已解决
    @RequestMapping(path = "/Resolved",method = RequestMethod.GET)
    public String getResolvedList(Model model,String accountname,Page page){
        List<Map<String,Object>> postresolvedListMap = postService.getResolvedList(accountname,page.getLimit(),page.getoffset());
        model.addAttribute("postListMap",postresolvedListMap);
        return "/resolved";
    }
}
