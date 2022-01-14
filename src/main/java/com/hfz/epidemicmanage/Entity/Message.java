package com.hfz.epidemicmanage.Entity;


import javax.websocket.Session;
import java.util.Date;

//消息通知
public class Message {
    private int id;
    private String content;
    private Date createTime;
    private String topic;
    private Session session;

    public Message() {
    }

    public Message(int id, String content, Date createTime, String topic, Session session) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.topic = topic;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", topic='" + topic + '\'' +
                ", session=" + session +
                '}';
    }
}
