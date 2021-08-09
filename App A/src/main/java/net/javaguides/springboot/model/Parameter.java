package net.javaguides.springboot.model;

import com.opencsv.bean.CsvBindByPosition;

import javax.persistence.*;


@Entity
@Table(name = "parameters")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "benefit")
    private float benefit;

    public Parameter() {
    }
    public Parameter(int seatNumber,float benefit ) {
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

