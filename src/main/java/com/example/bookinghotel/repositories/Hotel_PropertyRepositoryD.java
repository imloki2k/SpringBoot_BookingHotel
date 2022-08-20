package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Hotel_Property;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface Hotel_PropertyRepositoryD extends CrudRepository<Hotel_Property,Long> {
    ArrayList<Hotel_Property> findAll();

}
