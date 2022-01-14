package com.hfz.epidemicmanage.Entity;


import java.util.Date;

//通知事件
public class Event {
    private int id;
    private int patientNum;//确诊人数
    private int doubtNUm;//疑似人数
    private int divideNum;//隔离人数
    private int deathNum;//死亡人数
    private Date createtime;//刷新时间

    public Event() {
    }

    public Event(int id, int patientNum, int doubtNUm, int divideNum, int deathNum, Date createtime) {
        this.id = id;
        this.patientNum = patientNum;
        this.doubtNUm = doubtNUm;
        this.divideNum = divideNum;
        this.deathNum = deathNum;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(int patientNum) {
        this.patientNum = patientNum;
    }

    public int getDoubtNUm() {
        return doubtNUm;
    }

    public void setDoubtNUm(int doubtNUm) {
        this.doubtNUm = doubtNUm;
    }

    public int getDivideNum() {
        return divideNum;
    }

    public void setDivideNum(int divideNum) {
        this.divideNum = divideNum;
    }

    public int getDeathNum() {
        return deathNum;
    }

    public void setDeathNum(int deathNum) {
        this.deathNum = deathNum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", patientNum=" + patientNum +
                ", doubtNUm=" + doubtNUm +
                ", divideNum=" + divideNum +
                ", deathNum=" + deathNum +
                ", createtime=" + createtime +
                '}';
    }
}
