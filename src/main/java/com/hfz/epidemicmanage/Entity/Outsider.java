package com.hfz.epidemicmanage.Entity;

public class Outsider {
    private int id;
    private String name;//姓名
    private int accountid;//账号id
    private String source;//来处
    private String place;//去处
    private String cometime;//来时间
    private int status;//状态
    private String content;//来干嘛
    private int phone;
    private String idcard;
    private String leavetime;

    public Outsider() {
    }

    public Outsider(int id, String name, int accountid, String source, String place, String cometime,
                    int status, String content, int phone, String idcard, String leavetime) {
        this.id = id;
        this.name = name;
        this.accountid = accountid;
        this.source = source;
        this.place = place;
        this.cometime = cometime;
        this.status = status;
        this.content = content;
        this.phone = phone;
        this.idcard = idcard;
        this.leavetime = leavetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCometime() {
        return cometime;
    }

    public void setCometime(String cometime) {
        this.cometime = cometime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    @Override
    public String toString() {
        return "Outsider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountid=" + accountid +
                ", source='" + source + '\'' +
                ", place='" + place + '\'' +
                ", cometime='" + cometime + '\'' +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", phone=" + phone +
                ", idcard='" + idcard + '\'' +
                ", leavetime='" + leavetime + '\'' +
                '}';
    }
}
