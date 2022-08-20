package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Discount;
import com.example.bookinghotel.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public Optional<Discount> findById(Long id) {
        return discountRepository.findById(id);
    }
}
