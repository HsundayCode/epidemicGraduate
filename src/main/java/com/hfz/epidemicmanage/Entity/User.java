package com.hfz.epidemicmanage.Entity;

//用户详情
public class User {
    private int id;
    private int accountid;//账号
    private String name;//姓名
    private String sex;//性别
    private String age;//年龄
    private int idcard;//身份证
    private String locale;//地址
    private int phone;//手机号码
    private String birthday;//出生日期
    private int userType;//1-住户 2-外来

    private String status;//1-正常  2-中度 3-高危 4-感染
    private String place;//感染地方
    private String divide;//隔离地址
    private String trail;//轨迹
    private String occurrencetime;//感染日期

    public User() {
    }

    public User(int id, int accountid, String name, String sex, String age, int idcard, String locale, int phone, String birthday,
                int userType, String status, String place, String divide, String trail, String occurrencetime) {
        this.id = id;
        this.accountid = accountid;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.idcard = idcard;
        this.locale = locale;
        this.phone = phone;
        this.birthday = birthday;
        this.userType = userType;
        this.status = status;
        this.place = place;
        this.divide = divide;
        this.trail = trail;
        this.occurrencetime = occurrencetime;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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
        return "User{" +
                "id=" + id +
                ", accountid=" + accountid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", idcard=" + idcard +
                ", locale='" + locale + '\'' +
                ", phone=" + phone +
                ", birthday='" + birthday + '\'' +
                ", userType=" + userType +
                ", status='" + status + '\'' +
                ", place='" + place + '\'' +
                ", divide='" + divide + '\'' +
                ", trail='" + trail + '\'' +
                ", occurrencetime='" + occurrencetime + '\'' +
                '}';
    }
}
