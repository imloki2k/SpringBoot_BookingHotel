package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.RoomImage;
import com.example.bookinghotel.repositories.RoomImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoomImageImpl implements RoomImageService {
    @Autowired
    private RoomImageRepository roomImageRepository;

    @Override
    public Iterable<RoomImage> getAll() {
        return roomImageRepository.findAll();
    }

    @Override
    public Optional<RoomImage> getOne(Long id) {
        return roomImageRepository.findById(id);
    }

    @Override
    public RoomImage save(RoomImage roomImage) {
        return roomImageRepository.save(roomImage);
    }

    @Override
    public RoomImage delete(Long id) {
        return null;
    }
}
