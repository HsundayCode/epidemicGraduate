package com.hfz.epidemicmanage.Entity;

public class Activity {
    private int id;
    private String title;
    private String content;
    private String place;//活动地点
    private int personNum;//活动人数
    private String leader;//负责人
    private String actime;//开始时间
    private int leaderPhone;//咨询电话
    private int status;//状态

    public Activity() {
    }

    public Activity(int id, String title, String content, String place,
                    int personNum, String leader, String actime, int leaderPhone, int status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.place = place;
        this.personNum = personNum;
        this.leader = leader;
        this.actime = actime;
        this.leaderPhone = leaderPhone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getActime() {
        return actime;
    }

    public void setActime(String actime) {
        this.actime = actime;
    }

    public int getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(int leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", place='" + place + '\'' +
                ", personNum=" + personNum +
                ", leader='" + leader + '\'' +
                ", actime='" + actime + '\'' +
                ", leaderPhone=" + leaderPhone +
                ", status=" + status +
                '}';
    }
}
