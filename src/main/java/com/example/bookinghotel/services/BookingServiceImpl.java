package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Booking;
import com.example.bookinghotel.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Optional<Booking> findByRoomIdAndUserId(Long room_id, Long id_user) {
        return bookingRepository.findByRoomIdAndUserId(room_id, id_user);
    }


    @Override
    public Optional<Booking> findByRoomId(Long id) {
        return bookingRepository.findByRoomId(id);
    }

    @Override
    public Optional<Booking> findByStartDateAndUserIdAndRoomId(Long userId, Long roomId, LocalDate date) {
        return bookingRepository.findByStartDateAndUserIdAndRoomId(userId, roomId, date);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}
