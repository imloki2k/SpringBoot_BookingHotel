package com.example.bookinghotel.entities;


import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeOfPayment;



    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
//    @JsonIgnore
    private Bill bill;

    public Payment(Long id, String typeOfPayment) {
        this.id = id;
        this.typeOfPayment = typeOfPayment;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }
}
