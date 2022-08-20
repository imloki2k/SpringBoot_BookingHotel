package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Room;
import com.example.bookinghotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServicelmpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;


    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

//    @Override
//    public Optional<Room> findByHotelId(Long hotel_id) {
//        return roomRepository.findById(hotel_id);
//    }



}
