package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.TermOfUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TermOfUserRepository extends CrudRepository<TermOfUser, Integer> {

    Optional<TermOfUser> findById(Long id);

}
