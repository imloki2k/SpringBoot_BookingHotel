package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Review;

import java.util.Optional;

public interface ReviewService {
    Iterable<Review> findAll();

    Optional<Review> findById(Long id);

    Review save(Review review);

    Iterable<Review> findByRoomIdQuery(Long id);

    Object findAvgByRoomIdQuery(Long id);

}
