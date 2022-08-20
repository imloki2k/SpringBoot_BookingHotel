package com.example.bookinghotel.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity implements Serializable {
    private Byte rating;
    private String reviewBody;
    private Boolean active;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
//    @JsonIgnore
    private Booking booking;

    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
