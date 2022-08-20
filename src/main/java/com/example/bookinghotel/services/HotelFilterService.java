package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface HotelFilterService extends CrudRepository<Hotel,Long> {
    public ArrayList<Hotel> listHotelByStandardAndPropertyAndOrderByStandard(int standard, String property,String location, String orderby, Pageable pageable);
    public ArrayList<Hotel> listHotelByLocation(String location, Pageable pageable);
    public ArrayList<Hotel> listHotelByPropery(String location,String Property,Pageable pageable);
    public ArrayList<Hotel> listHotelByStandard(String location,int standard,Pageable pageable);

    // no pageable
    public ArrayList<Hotel> listHotelByStandardAndPropertyAndOrderByStandardAndNoPageable(int standard, String property,String location, String orderby);
    public ArrayList<Hotel> listHotelByLocationAndNoPageable(String location);
    public ArrayList<Hotel> listHotelByProperyAndNoPageable(String location,String Property);
    public ArrayList<Hotel> listHotelByStandardAndNoPageable(String location,int standard);




}
