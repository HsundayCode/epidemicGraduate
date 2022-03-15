package com.hfz.epidemicmanage.Controller;

import com.hfz.epidemicmanage.Dao.AccountMapper;
import com.hfz.epidemicmanage.Dao.UserMapper;
import com.hfz.epidemicmanage.Entity.Account;
import com.hfz.epidemicmanage.Entity.Page;
import com.hfz.epidemicmanage.Entity.Post;
import com.hfz.epidemicmanage.Service.PostService;
import com.hfz.epidemicmanage.Util.EpidemicConstant;
import com.hfz.epidemicmanage.Util.GetJSONUtil;
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
import java.util.Map;

@Controller
public class PostController implements EpidemicConstant {
    @Autowired
    PostService postService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountMapper accountMapper;


    @RequestMapping(path = "/addfeedback",method = RequestMethod.GET)
    public String getAddPostPage(){
        return "addTemplate/addfeedback";
    }
    //提出问题
    //这里应该返回个状态码，表示是否提交成功，要改成ajax返回json
    @LoginRequire
    @RequestMapping(path = "/addPost",method = RequestMethod.POST)
    public String addPost(Model model,String title,String content){
        postService.addPost(title,content);
        model.addAttribute("res","反馈到位");
        return "/addresult";
    }

    //获取详细信息
    @LoginRequire
    @ManageRequire
    @ResponseBody
    @RequestMapping(path = "/postdetail/{postid}",method = RequestMethod.GET)
    public String PostDetail(Model model,@PathVariable("postid") int postid){
        Post postdetail = postService.FindPostDetail(postid);
        model.addAttribute("postdetail",postdetail);//反馈详情

        Account account = accountMapper.selectById(postdetail.getAccountid());
        model.addAttribute("account",account);//账号显示

        return GetJSONUtil.toJSON(postdetail.getContent());
        //return "/postdetail";//因为账号和用户是分开两个表，所以要查询两次，返回两个对象
    }

    //修改解决未解决状态
    //这里应该返回个状态码，表示是否成功，要改成ajax返回json
    @LoginRequire
    @ManageRequire
    @RequestMapping(path = "/updatestatus/{postid}/{poststatus}",method = RequestMethod.GET)
    public String updateStatus(Model model,@PathVariable("postid")int postid,@PathVariable("poststatus")int status){
        postService.updateStatus(postid,status);
        return "redirect:/postdetail/"+postid;
    }

    //获取全部反馈信息列表
    @LoginRequire
    @ManageRequire
    @RequestMapping(path = "/postList",method = RequestMethod.GET)
    public String getPostList(Model model,Page page){
        page.setPath("/postList");
        page.setLimit(5);
        List<Map<String,Object>> postListMap =  postService.getPostList(page.getLimit(),page.getoffset());
        model.addAttribute("postListMap",postListMap);
        return "views/feedback";
    }



    //查询未解决反馈信息
    @LoginRequire
    @ManageRequire
    @RequestMapping(path = "/unResolved",method = RequestMethod.GET)
    public String getUnResolvedList(Model model,Page page){
        List<Map<String,Object>> postUnresolvedListMap = postService.getUnResolvedList(page.getLimit(),page.getoffset());
        model.addAttribute("postListMap",postUnresolvedListMap);
        return "views/feedback";
    }

    //查询已解决反馈信息
    @LoginRequire
    @ManageRequire
    @RequestMapping(path = "/Resolved",method = RequestMethod.GET)
    public String getResolvedList(Model model,Page page){
        List<Map<String,Object>> postresolvedListMap = postService.getResolvedList(page.getLimit(),page.getoffset());
        model.addAttribute("postListMap",postresolvedListMap);
        return "views/feedback";
    }
}
