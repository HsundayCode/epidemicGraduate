package com.hfz.epidemicmanage.Entity;


//病人详情
public class Patient {
    private int id;
    private int userid;//用户id
    private String name;//姓名
    private String sex;//性别
    private String age;//年龄
    private int idcard;//身份证
    private String locale;//地址
    private int phone;//手机号码
    private String birthday;//出生日期
    private String status;//隔离情况级别
    private String place;//感染地方
    private String divide;//隔离地址
    private String trail;//轨迹
    private String occurrencetime;//感染日期

    public Patient() {
    }

    public Patient(int id, int userid, String name, String sex, String age,
                   int idcard, String locale, int phone, String birthday, String status,
                   String place, String divide, String trail, String occurrencetime) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.idcard = idcard;
        this.locale = locale;
        this.phone = phone;
        this.birthday = birthday;
        this.status = status;
        this.place = place;
        this.divide = divide;
        this.trail = trail;
        this.occurrencetime = occurrencetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getIdcard() {
        return idcard;
    }

    public void setIdcard(int idcard) {
        this.idcard = idcard;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDivide() {
        return divide;
    }

    public void setDivide(String divide) {
        this.divide = divide;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

    public String getOccurrencetime() {
        return occurrencetime;
    }

    public void setOccurrencetime(String occurrencetime) {
        this.occurrencetime = occurrencetime;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", idcard=" + idcard +
                ", locale='" + locale + '\'' +
                ", phone=" + phone +
                ", birthday='" + birthday + '\'' +
                ", status='" + status + '\'' +
                ", place='" + place + '\'' +
                ", divide='" + divide + '\'' +
                ", trail='" + trail + '\'' +
                ", occurrencetime='" + occurrencetime + '\'' +
                '}';
    }
}
