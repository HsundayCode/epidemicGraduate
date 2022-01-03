package com.hfz.epidemicmanage.Entity;


import java.util.Date;

    //账号
    public class Account {
        private int id;
        private String name;//账号 姓名
        private String password;//密码
        private String email;//激活邮箱
        private String salt;//密码加密
        private int status;//账号状态
        private String activationcode;//激活码
        private Date createtime;//创建时间
        private int type;//账号权限

        public Account() {
        }

        public Account(int id, String name, String password, String email, String salt, int status, String activationcode, Date createtime, int type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.status = status;
        this.activationcode = activationcode;
        this.createtime = createtime;
        this.type = type;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getActivationcode() {
        return activationcode;
    }

    public void setActivationcode(String activationcode) {
        this.activationcode = activationcode;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", salt=" + salt +
                ", status=" + status +
                ", activationcode=" + activationcode +
                ", createtime=" + createtime +
                ", type=" + type +
                '}';
    }


}
