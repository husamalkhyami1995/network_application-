package com.karam.applicationB.dto;

public class ReceivedData {
    private String email ;
    private String content;
    private String ids ;
    private Float sum ;

    public ReceivedData() {}
    public ReceivedData( String email , String content , String ids , Float sum ) {
        this.email = email ;
        this.content = content ;
        this.ids = ids ;
        this.sum = sum ;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    @Override
    public String toString () {
        return this.getEmail() + " / " + this.getContent() + " / " + this.getIds() + " / " + this.getSum().toString() ;
    }
}
