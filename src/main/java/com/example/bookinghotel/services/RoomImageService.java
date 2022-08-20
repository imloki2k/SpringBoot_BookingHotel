package com.example.bookinghotel.services;



import com.example.bookinghotel.entities.RoomImage;

import java.util.Optional;

public interface RoomImageService {
    Iterable<RoomImage> getAll();
    Optional<RoomImage> getOne(Long id);
    RoomImage save(RoomImage roomImage);
    RoomImage delete(Long id);
}
