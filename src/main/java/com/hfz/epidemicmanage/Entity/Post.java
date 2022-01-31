package com.hfz.epidemicmanage.Entity;

import java.util.Date;

//意见
//反馈
//疫情上报
public class Post {
    private int id;
    private int accountid;
    private String title;
    private String content;
    private int status;
    private Date createTime;

    public Post() {
    }

    public Post(int id, int accountid, String title, String content, int status, Date createTime) {
        this.id = id;
        this.accountid = accountid;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", accountid=" + accountid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}