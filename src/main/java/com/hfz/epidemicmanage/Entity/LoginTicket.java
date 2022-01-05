package com.hfz.epidemicmanage.Entity;

import java.util.Date;

public class LoginTicket {
    private int id;
    private String username;
    private String ticket;
    private Date expired;
    private int status;

    public LoginTicket() {
    }

    public LoginTicket(int id, String username, String ticket, Date expired, int status) {
        this.id = id;
        this.username = username;
        this.ticket = ticket;
        this.expired = expired;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginTicket{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", ticket='" + ticket + '\'' +
                ", expired=" + expired +
                ", status=" + status +
                '}';
    }
}
