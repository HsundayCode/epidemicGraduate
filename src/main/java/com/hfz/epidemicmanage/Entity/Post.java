package com.hfz.epidemicmanage.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//意见
//反馈
//疫情上报
public class Post {
    private int id;
    private int accountid;
    private String name;
    private String title;
    private String content;
    private int status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Post() {
    }

    public Post(int id, int accountid, String name, String title, String content, int status, Date createTime) {
        this.id = id;
        this.accountid = accountid;
        this.name = name;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
