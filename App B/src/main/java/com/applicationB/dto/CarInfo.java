package com.karam.applicationB.dto;

public class CarInfo {
    private String name ;
    private Long price ;
    private Long seats ;
    public CarInfo() {}
    public CarInfo(String name, Long price, Long seats) {
        this.name = name;
        this.price = price;
        this.seats = seats;
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
}
