package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<Room> findById(Long id);
    // Role admin find room (Creatỏ: QuangNHHE130717
    @Query(value = "SELECT rooms.*,hotel.name_of_hotel from rooms inner join hotel on rooms.hotel_id = hotel.id", nativeQuery = true)
    Iterable<Room> findAllRoomWithAdmin();

    @Query(value = "SELECT rooms.*,hotel.name_of_hotel from rooms inner join hotel on rooms.hotel_id = hotel.id where hotel.name_of_hotel like concat('%',?1,'%')", nativeQuery = true)
    Iterable<Room> findAllRoomWithAdminWithNameHotel(String name);
}
