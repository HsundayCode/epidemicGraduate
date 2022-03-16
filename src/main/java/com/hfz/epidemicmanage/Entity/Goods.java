package com.hfz.epidemicmanage.Entity;

public class Goods {

    private int id;
    private String name;
    private int number;
    private String source;
    private String place;
    private int status;
    private int accountid;
    private String Modifier;

    public Goods() {
    }

    public Goods(int id, String name, int number, String source, String place, int status, int accountid, String modifier) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.source = source;
        this.place = place;
        this.status = status;
        this.accountid = accountid;
        Modifier = modifier;
    }

    public String getModifier() {
        return Modifier;
    }

    public void setModifier(String modifier) {
        Modifier = modifier;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", source='" + source + '\'' +
                ", place='" + place + '\'' +
                ", status=" + status +
                '}';
    }
}
