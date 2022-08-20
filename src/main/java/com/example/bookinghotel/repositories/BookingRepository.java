package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Booking;
import com.example.bookinghotel.entities.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query(value = "select bookings.* from bookings left join rooms on rooms.id = bookings.room_id left join users on bookings.user_id = users.id where bookings.room_id = ?1 and bookings.user_id = ?2 limit 1", nativeQuery = true)
    Optional<Booking> findByRoomIdAndUserId(Long room_id, Long id_user);

    Optional<Booking> findByRoomId(Long id);

    @Query(value = "select * from bookings where user_id = ?1 and room_id = ?2 and start_date = ?3 limit 1", nativeQuery = true)
    Optional<Booking> findByStartDateAndUserIdAndRoomId(Long userId, Long roomId, LocalDate date);


    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id FROM booking_hotelver10.bookings " +
                   "inner join rooms on bookings.room_id = rooms.id "  +
                   "where rooms.hotel_id = ?1 and bookings.start_date >= ?2 and bookings.status = 1",nativeQuery = true)
    Iterable<Booking> findByStartDate(Long id,LocalDate date1);


    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "where rooms.hotel_id = ?1 and bookings.end_date <= ?2 and bookings.status = 1",nativeQuery = true)
    Iterable<Booking> findByEndDate(Long id,LocalDate date2);


    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "where rooms.hotel_id = ?1 and bookings.start_date >= ?2 and bookings.end_date <= ?3 and bookings.status = 1",nativeQuery = true)
    Iterable<Booking> findByStartDateAndEndDate(Long id,LocalDate date1, LocalDate date2);


    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "where rooms.hotel_id = ?1 and bookings.status = 1",nativeQuery = true)
    Iterable<Booking> findAllBookingByHotelId(Long id);

    Iterable<Booking> findAll();


    // Get data booking for user (Creator: QUangNHHE130717)
    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where bookings.user_id = ?1 and bookings.status=1",nativeQuery = true)
    Iterable<Booking> findAllBookingByUserId(Long id);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where bookings.user_id = ?1 and bookings.start_date >= ?2 and bookings.status=1",nativeQuery = true)
    Iterable<Booking> findAllBookingByUserIdAndStartDate(Long id,LocalDate dateStar);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where bookings.user_id = ?1 and bookings.end_date <= ?2 and bookings.status=1",nativeQuery = true)
    Iterable<Booking> findAllBookingByUserIdAndEndDate(Long id,LocalDate dateEnd);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where bookings.user_id = ?1 and bookings.start_date >= ?2 and bookings.end_date <= ?3 and bookings.status=1",nativeQuery = true)
    Iterable<Booking> findAllBookingByUserIdAndStartDateEndDate(Long id,LocalDate dateStart,LocalDate dateEnd);

//    @Query(value = "DELETE FROM bookings WHERE id=?1",nativeQuery = true)
//    boolean deleteBookingById(Long id);

    // Get data booking for Admin (Creator: QUangNHHE130717)
    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "  inner join users on bookings.user_id = users.id " +
            "inner join hotel on rooms.hotel_id = hotel.id where bookings.status=1 ",nativeQuery = true)
    Iterable<Booking> findAllBookingAdmin();

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "  inner join users on bookings.user_id = users.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where  bookings.start_date >= ?1",nativeQuery = true)
    Iterable<Booking> findAllBookingByAdminAndStartDate(LocalDate dateStar);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "  inner join users on bookings.user_id = users.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where bookings.end_date <= ?1",nativeQuery = true)
    Iterable<Booking> findAllBookingByAdminAndEndDate(LocalDate dateEnd);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "  inner join users on bookings.user_id = users.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where bookings.start_date >= ?1 and bookings.end_date <= ?2",nativeQuery = true)
    Iterable<Booking> findAllBookingAdminAndStartDateEndDate(LocalDate dateStart,LocalDate dateEnd);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "  inner join users on bookings.user_id = users.id " +
            "inner join hotel on rooms.hotel_id = hotel.id where bookings.status=1 and users.name like concat('%',?1,'%')",nativeQuery = true)
    Iterable<Booking> findAllBookingAdminAndNameUser(String nameUser);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "  inner join users on bookings.user_id = users.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where  bookings.start_date >= ?1 and users.name like concat('%',?2,'%')",nativeQuery = true)
    Iterable<Booking> findAllBookingByAdminAndStartDateAndNameUser(LocalDate dateStar, String nameUser);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "  inner join users on bookings.user_id = users.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where bookings.end_date <= ?1 and users.name like concat('%',?2,'%')",nativeQuery = true)
    Iterable<Booking> findAllBookingByAdminAndEndDateAndNameUser(LocalDate dateEnd, String nameUser);

    @Query(value = "SELECT bookings.id,bookings.start_date,bookings.end_date,bookings.number_of_guests,bookings.room_id,bookings.num_night,bookings.total_prize,bookings.status,bookings.user_id,rooms.name,hotel.name_of_hotel FROM booking_hotelver10.bookings " +
            "inner join rooms on bookings.room_id = rooms.id " +
            "  inner join users on bookings.user_id = users.id " +
            "inner join hotel on rooms.hotel_id = hotel.id " +
            "where bookings.start_date >= ?1 and bookings.end_date <= ?2 and users.name like concat('%',?3,'%')",nativeQuery = true)
    Iterable<Booking> findAllBookingAdminAndStartDateEndDateAndNameUser(LocalDate dateStart,LocalDate dateEnd, String nameUser);

}
