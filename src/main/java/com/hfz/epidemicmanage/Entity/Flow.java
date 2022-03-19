package com.hfz.epidemicmanage.Entity;

import java.util.Date;

public class Flow {
    private int id;
    private int userType;//0-外来 1-普通
    private String name;
    private Date recordTime;
    private int flowType;//0-出 1-或入
    private String place;

    public Flow() {
    }

    public Flow(int id, int userType, String name, Date recordTime, int flowType, String place) {
        this.id = id;
        this.userType = userType;
        this.name = name;
        this.recordTime = recordTime;
        this.flowType = flowType;
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public int getFlowType() {
        return flowType;
    }

    public void setFlowType(int flowType) {
        this.flowType = flowType;
    }
}
