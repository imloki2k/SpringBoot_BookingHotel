package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.RoomImage;
import com.example.bookinghotel.entities.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RoomImageRepository extends CrudRepository<RoomImage, Long> {


    @Query(value = "SELECT room_images.id, room_images.image_url,room_images.room_id FROM room_images " +
            "INNER JOIN rooms ON room_images.room_id = rooms.id " +
            "WHERE rooms.id = ?1", nativeQuery = true)
    List<RoomImage> listRoomImage(Long room_id);


}
