package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingOfCus extends CrudRepository<Booking, Long> {
    @Query(nativeQuery = true, value = "SELECT bookings.* from bookings left join rooms on bookings.room_id = rooms.id left join users on rooms.user_id = users.id where users.id = ?1")
    Iterable<Booking> getBookingsOfCus(Long id);
}
