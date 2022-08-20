package com.example.bookinghotel.entities;

import javax.persistence.*;

@Entity
@Table(name = "TermOfUser")
public class TermOfUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    public TermOfUser() {
    }

    public TermOfUser(String name) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
