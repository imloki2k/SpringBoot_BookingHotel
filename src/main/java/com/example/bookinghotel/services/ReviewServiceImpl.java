package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Review;
import com.example.bookinghotel.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Iterable<Review> findByRoomIdQuery(Long id) {
        return reviewRepository.findByRoomIdQuery(id);
    }

    @Override
    public Object findAvgByRoomIdQuery(Long id) {
        return reviewRepository.findAvgRattingByRoomIdQuery(id);
    }
}
