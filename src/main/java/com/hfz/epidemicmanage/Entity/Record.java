package com.hfz.epidemicmanage.Entity;


import java.util.Date;

//每日打卡
public class Record {
    private int id ;
    private int accountid;
    private int temperature;//当前体温
    private String name;//姓名
    private String locale;//地址
    private int phone;//联系
    private Date createtime;//打卡时间

}
