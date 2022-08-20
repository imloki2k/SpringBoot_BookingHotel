package com.example.bookinghotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "bookings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking extends AbstractEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "number_of_guests")
    private int numberOfGuests;

//    @Column(name = "number_of_children")
//    private Byte numberOfChildren;
//
//    @Column(name = "number_of_infants")
//    private Byte numberOfInfants;

    private LocalDate startDate;
    private LocalDate endDate;


    @Column(name = "status")
    private int status;

    @Column(name = "num_Night")
    private int numNight;

    @Column(name = "total_Prize")
    private double price;

    // fetch = FetchType.LAZY khi select đối tượng Booking thì mặc định không query các đối tượng User liên quan.
    // CascadeType.ALL Tương ứng với tất cả các loại cascade. cascade={DETACH, MERGE, PERSIST, REFRESH, REMOVE}
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
//    @JsonIgnore
    private UserInfo user;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "booking")
    @JsonIgnore
    private List<Review> reviews;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
//    @JsonIgnore
    private Room room;

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumNight() {
        return numNight;
    }

    public void setNumNight(int numNight) {
        this.numNight = numNight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
