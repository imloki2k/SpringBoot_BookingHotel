package com.example.bookinghotel.repositories;

import com.example.bookinghotel.entities.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface HotelFilterRepository extends JpaRepository<Hotel,Long> {

    //phan dung code
    // tim theo property va standard cua hotel
    @Query(nativeQuery = true,value = "select hotel.* from hotel inner join hotel_property " +
            "on hotel.hotel_property_id = hotel_property.id  " +
            "where hotel.hotel_standard = ?1 and hotel_property.type like concat('%',?2,'%') and hotel.address_of_hotel like concat('%',?3,'%')")
    public ArrayList<Hotel> listHotelByStandardAndProperty(int standard, String property,String location, Pageable pageable);

    // tim theo property va standard cua hotel va order by standard
    @Query(nativeQuery = true,value = "select hotel.* from hotel inner join hotel_property " +
            "on hotel.hotel_property_id = hotel_property.id  " +
            "where hotel.hotel_standard = ?1 and hotel_property.type like concat('%',?2,'%') and hotel.address_of_hotel like concat('%',?3,'%') " +
            "order by hotel.hotel_standard ?4")
    public ArrayList<Hotel> listHotelByStandardAndPropertyAndOrderByStandard(int standard, String property,String location, String orderBy, Pageable pageable);
    @Query(nativeQuery = true, value="select hotel.* from hotel where hotel.address_of_hotel like concat('%',?1,'%')")
    public ArrayList<Hotel> listHotelByLocationAndPageable(String location,Pageable pageable);
    @Query(nativeQuery = true, value="select hotel.* from hotel where hotel.address_of_hotel like concat('%',?1,'%') and hotel.hotel_standard = ?2")
    public ArrayList<Hotel> listHotelByLocationAndStandardAndPageable(String location,int standard,Pageable pageable);
    @Query(nativeQuery = true, value="select hotel.* from hotel inner join hotel_property " +
            " on hotel.hotel_property_id = hotel_property.id where hotel.address_of_hotel like concat('%',?1,'%') and hotel_property.type like concat('%',?2,'%')")
    public ArrayList<Hotel> listHotelByLocationAndPropertyAndPageable(String location,String property,Pageable pageable);

    // tim theo property va standard cua hotel va order by standard va khong phan trang
    @Query(nativeQuery = true,value = "select hotel.* from hotel inner join hotel_property " +
            "on hotel.hotel_property_id = hotel_property.id  " +
            "where hotel.hotel_standard = ?1 and hotel_property.type like concat('%',?2,'%') and hotel.address_of_hotel like concat('%',?3,'%')")
    public ArrayList<Hotel> listHotelByStandardAndPropertyAndNoPageable(int standard, String property,String location);


    @Query(nativeQuery = true,value = "select hotel.* from hotel inner join hotel_property " +
            "on hotel.hotel_property_id = hotel_property.id  " +
            "where hotel.hotel_standard = ?1 and hotel_property.type like concat('%',?2,'%') and hotel.address_of_hotel like concat('%',?3,'%') " +
            "order by hotel.hotel_standard ?4")
    public ArrayList<Hotel> listHotelByStandardAndPropertyAndOrderByStandardAndNoPageable(int standard, String property,String location, String orderBy);
    @Query(nativeQuery = true, value="select hotel.* from hotel where hotel.address_of_hotel like concat('%',?1,'%')")
    public ArrayList<Hotel> listHotelByLocationAndNoPageable(String location);
    @Query(nativeQuery = true, value="select hotel.* from hotel where hotel.address_of_hotel like concat('%',?1,'%') and hotel.hotel_standard = ?2")
    public ArrayList<Hotel> listHotelByLocationAndStandardAndNoPageable(String location,int standard);
    @Query(nativeQuery = true, value="select hotel.* from hotel inner join hotel_property " +
            " on hotel.hotel_property_id = hotel_property.id where hotel.address_of_hotel like concat('%',?1,'%') and hotel_property.type like concat('%',?2,'%')")
    public ArrayList<Hotel> listHotelByLocationAndPropertyAndNoPageable(String location,String property);

    //phan lay thong tin property and standard

    @Query(nativeQuery = true,value = "select DISTINCT  hotel from hotel join rooms on hotel.id = rooms.hotel_id " +
            "where hotel.name_of_hotel like concat('%',?1,'%') and rooms.total_of_bedroom = ?2")
    public List<Hotel> getHotelByLocationAndNumberOfPeople(String location, int number_of_people);

    //get name of hotel where room is not booked
    @Query(nativeQuery = true,value = "select DISTINCT  hotel.* from hotel inner join rooms on hotel.id = rooms.hotel_id " +
            " where  hotel.address_of_hotel like concat('%',?1,'%') and rooms.total_of_bedroom >= ?2 and " +
            "hotel.address_of_hotel not in( " +
            "select hotel.address_of_hotel from hotel " +
            " join rooms" +
            " on hotel.id = rooms.hotel_id  join bookings on rooms.id = bookings.room_id)")
    public Optional<ArrayList<Hotel>> getHotelsByNameAndNumberOfPeopleNoBookedList(String location, int number_of_people);

    // get name of hotel where room is booked
    @Query(nativeQuery = true,value =
            "select DISTINCT hotel.* from hotel " +
            " join rooms " +
            " on hotel.id = rooms.hotel_id  join bookings on rooms.id = bookings.room_id " +
            "        where hotel.address_of_hotel like concat('%',?1,'%') and " +
            " rooms.total_of_bedroom >= ?2")
    public Optional<ArrayList<Hotel>> getHotelsByNameAndNumberOfPeopleBookedList(String location,int number_of_people);



    // phan dung code-end

}
