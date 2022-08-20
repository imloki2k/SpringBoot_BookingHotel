package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Discount;
import com.example.bookinghotel.repositories.DiscountRepository;

import java.util.Optional;

public interface DiscountService {
    Optional<Discount> findById(Long id);
}
