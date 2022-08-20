package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HomeRepository extends CrudRepository<Room, Long> {

    @Query("SELECT h FROM Room h")
    Iterable<Room> findAllCustomQuery();


    @Query(value = "SELECT * FROM rooms where hotel_id = ?1", nativeQuery = true)
    Iterable<Room> findAllRoomByHotelId(long id);


    @Query(value = "SELECT * FROM rooms where address like ?1", nativeQuery = true)
    Iterable<Room> findAllRoomByAddress(String add);

    //    @Query("SELECT h.id, h.name, h.description, h.address, h.pricePerNight, h.numBedrooms, h.numBathrooms FROM Home h WHERE h.id = ?1")
//    @Query(value = "SELECT id, name, description, address, price_per_night, total_of_bedroom, total_of_bathroom, status,province_id, property_type, user_id FROM rooms WHERE id = ?1", nativeQuery = true)
    @Query(value = "SELECT * FROM rooms WHERE id = ?1", nativeQuery = true)
    Optional<Room> findByHomeId(Long id);

    @Query(nativeQuery = true,value = "call GetLastRoom")
    Room GetLastRoom();
}

