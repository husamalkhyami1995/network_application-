package com.karam.applicationB.dto;

public class SelledCar {
    private Long id ;
    private String sellDate ;
    private String customer ;

    public SelledCar() {}
    public SelledCar(Long id, String sellDate, String customer) {
        this.id = id;
        this.sellDate = sellDate;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
