package com.example.bookinghotel.entities;


import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "report")
public class Report {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Name is not empty")
    @NotNull
    private String name;


    @NotEmpty
    @Email(message = "Email is not valid")
    private String email;

    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[0-9]{10}" ,message = "Phone number is invalid" )
    @NotNull
    @NotEmpty
    private String phoneNumber;

    @Size(max = 1000, min = 1)
    @NotNull
    @NotEmpty(message = "Message is not empty")
    private String message;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Report(){

    }

    public Report(Long id, String name, String email, String phoneNumber, String message, Hotel hotel, Room room) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.hotel = hotel;
        this.room = room;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
