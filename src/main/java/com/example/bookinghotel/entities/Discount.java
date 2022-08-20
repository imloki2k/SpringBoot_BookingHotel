package com.example.bookinghotel.entities;


import javax.persistence.*;

@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double disRate;

    public Discount() {
    }

    public Discount( Double disRate) {

        this.disRate = disRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDisRate() {
        return disRate;
    }

    public void setDisRate(Double disRate) {
        this.disRate = disRate;
    }
}
