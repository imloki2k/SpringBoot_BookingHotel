package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(String name);

}
