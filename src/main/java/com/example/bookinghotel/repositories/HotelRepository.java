package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.entities.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
//    phan dung code
    @Query(nativeQuery = true,value = "select Hotel.* from Hotel left join Hotel_Property " +
            "on Hotel_Property_id = Hotel_property.id " +
            "where hotel.address_of_hotel LIKE CONCAT('%',?1,'%') and Hotel_Property.type LIKE CONCAT('%',?2,'%') ")
    Iterable<Hotel> findByHotel_addressContainingAndHotel_property(String contains,String hotelProperty);
    @Query(nativeQuery = true,value = "select hotel.* from hotel where hotel.name_of_hotel like ?1")
    Optional<Hotel> findByNameOfHotel(String hotel_name);


    // phan dung code-end
    @Query(value = "SELECT * FROM hotel where user_id = ?1", nativeQuery = true)
    Iterable<Hotel> findAllHotelByUserId(long id);

    @Query(value = "select hotel.* from hotel where hotel.name_of_hotel like concat('%',?1,'%')", nativeQuery = true)
    Iterable<Hotel> findByAllHotelWithName(String nameHotel);
}
