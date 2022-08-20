package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.TermOfUser;

import java.util.Optional;

public interface TermOfUserService {
    Optional<TermOfUser> findById(Long id);

    TermOfUser save(TermOfUser termOfUser);

    Iterable<TermOfUser> findAll();
}
