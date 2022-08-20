package com.example.bookinghotel.repositories;

import com.example.bookinghotel.entities.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HotelReportRepository extends CrudRepository<Report,Long> {

    Optional<Report> findById(Long id);


    Iterable<Report> findAll();

    @Query(value = "SELECT report.id,report.email,report.message,report.name,report.phone_number,report.hotel_id,report.room_id FROM booking_hotelver10.report\n" +
            "left join hotel on report.hotel_id = hotel.id\n" +
            "where hotel.name_of_hotel like concat('%',?1,'%') and report.message like concat('%',?2,'%')",nativeQuery = true)
    Iterable<Report> findByNameOfHotelAndMessage(String name, String message);

    @Query(value = "SELECT report.id,report.email,report.message,report.name,report.phone_number,report.hotel_id,report.room_id FROM booking_hotelver10.report\n" +
            "left join hotel on report.hotel_id = hotel.id\n" +
            "where hotel.name_of_hotel like concat('%',?1,'%')",nativeQuery = true)
    Iterable<Report> findByNameOfHotel(String name);

    @Query(value = "SELECT report.id,report.email,report.message,report.name,report.phone_number,report.hotel_id,report.room_id FROM booking_hotelver10.report\n" +
            "left join hotel on report.hotel_id = hotel.id\n" +
            "where report.message like concat('%',?1,'%')",nativeQuery = true)
    Iterable<Report> findByMessage(String message);

}
