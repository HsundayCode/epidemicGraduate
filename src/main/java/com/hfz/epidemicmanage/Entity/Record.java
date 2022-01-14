package com.hfz.epidemicmanage.Entity;


import java.util.Date;

//每日打卡
public class Record {
    private int id ;
    private int accountid;
    private String temperature;//当前体温
    private String name;//姓名
    private String locale;//当前地址
    private int phone;//联系
    private String createtime;//打卡时间

    public Record() {
    }

    public Record(int id, int accountid, String temperature, String name, String locale, int phone, String createtime) {
        this.id = id;
        this.accountid = accountid;
        this.temperature = temperature;
        this.name = name;
        this.locale = locale;
        this.phone = phone;
        this.createtime = createtime;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", accountid=" + accountid +
                ", temperature=" + temperature +
                ", name='" + name + '\'' +
                ", locale='" + locale + '\'' +
                ", phone=" + phone +
                ", createtime=" + createtime +
                '}';
    }
}
