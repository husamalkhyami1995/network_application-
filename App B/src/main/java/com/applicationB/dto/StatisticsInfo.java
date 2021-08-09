package com.karam.applicationB.dto;

import java.io.Serializable;

public class StatisticsInfo implements Serializable {
    private String email ;
    private String content ;
    private String  date ;

    public StatisticsInfo () {}
    public StatisticsInfo ( String email ,String content, String date ) {
        this.email = email ;
        this.content = content ;
        this.date = date ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "StatisticsInfo{" +
                "email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
