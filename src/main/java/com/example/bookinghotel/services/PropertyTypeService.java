package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.PropertyType;

import java.util.Optional;

public interface PropertyTypeService {
    Iterable<PropertyType> getAll();
    Optional<PropertyType> getOne(Long id);
    PropertyType save(PropertyType propertyType);
    PropertyType delete(Long id);
}
