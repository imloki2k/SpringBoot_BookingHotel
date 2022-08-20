package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Room;
import com.example.bookinghotel.repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeRepository homeRepository;

    @Override
    public Iterable<Room> findAll() {
        return homeRepository.findAll();
    }

    @Override
    public Iterable<Room> findAllByHotelId(long id) {
        return homeRepository.findAllRoomByHotelId(id);
    }

    @Override
    public Iterable<Room> findAllByAddress(String add) {
        return homeRepository.findAllRoomByAddress(add);
    }

    @Override
    public Iterable<Room> findAllCustomQuery() {
        return homeRepository.findAllCustomQuery();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return homeRepository.findById(id);
    }

    @Override
    public Optional<Room> findByHomeId(Long id) {
        return homeRepository.findByHomeId(id);
    }

    @Override
    public Room save(Room room) {
        return homeRepository.save(room);
    }

    @Override
    public Optional<Room> deleteById(Long id) {
        Optional<Room> home = homeRepository.findById(id);
        homeRepository.deleteById(id);
        return home;
    }
}
