package com.karam.applicationB.models;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "seats")
    private Long seats;

    @Column(name = "sell_date")
    private String sellDate;

    @Column(name = "sell_price")
    private Float sellPrice;

    @Column(name = "customer")
    private String customer;

    @CreationTimestamp
    private ZonedDateTime createdOn;
    @UpdateTimestamp
    private ZonedDateTime updatedOn;
    @Version
    private Long version;

    public Car ()  {}

    public Car(String name, Long price, Long seats, String sellDate, Float sellPrice, String customer ) {
        this.name = name ;
        this.price = price ;
        this.seats = seats ;
        this.sellDate = sellDate ;
        this.sellPrice = sellPrice ;
        this.customer = customer ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSeats() {
        return seats;
    }

    public void setSeats(Long seats) {
        this.seats = seats;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
