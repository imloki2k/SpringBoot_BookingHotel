package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoomPaging extends PagingAndSortingRepository<Room, Long> {
    Page<Room> findAllByUserId(long user_id, Pageable pageable);

}
