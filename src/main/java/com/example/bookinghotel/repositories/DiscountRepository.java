package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {

    Optional<Discount> findById(Long id);
}
