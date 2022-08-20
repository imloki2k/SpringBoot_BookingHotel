package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Room;
import com.example.bookinghotel.repositories.RoomRepository;

import java.util.Optional;

public interface RoomService{

    Optional<Room> findById(Long id);

//    Optional<Room> findByHotelId(Long hotel_id);
}
