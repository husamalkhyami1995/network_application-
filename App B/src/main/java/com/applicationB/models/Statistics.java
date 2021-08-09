package com.karam.applicationB.models;


import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email ;

    @Column(name = "content")
    private String content ;

    @Column(name = "ids")
    private String ids ;

    @Column(name = "sum")
    private Float sum ;

    public Statistics () {}
    public Statistics( String email , String content , String ids , Float sum ) {
        this.email = email ;
        this.content = content ;
        this.ids  = ids ;
        this.sum = sum ;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}