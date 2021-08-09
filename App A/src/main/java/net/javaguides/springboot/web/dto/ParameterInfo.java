package net.javaguides.springboot.web.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ParameterInfo {


    private Long id;

    private int seatNumber;

    private float benefit;

    public ParameterInfo() {
    }
    public ParameterInfo( Long id , int seatNumber,float benefit ) {
        this.id = id ;
        this.seatNumber = seatNumber;
        this.benefit = benefit;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public float getBenefit() {
        return benefit;
    }

    public void setBenefit(float benefit) {
        this.benefit = benefit;
    }
}
