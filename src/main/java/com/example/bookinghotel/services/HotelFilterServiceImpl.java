package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.repositories.HotelFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.Optional;

public class HotelFilterServiceImpl implements HotelFilterService {
    //phan dung code
    @Autowired
    HotelFilterRepository hotelFilterRepo;

    @Override
    public <S extends Hotel> S save(S entity) {
        return hotelFilterRepo.save(entity);
    }

    @Override
    public <S extends Hotel> Iterable<S> saveAll(Iterable<S> entities) {
        return hotelFilterRepo.saveAll(entities);
    }

    @Override
    public Optional<Hotel> findById(Long aLong) {
        return hotelFilterRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return hotelFilterRepo.existsById(aLong);
    }

    @Override
    public Iterable<Hotel> findAll() {
        return hotelFilterRepo.findAll();
    }

    @Override
    public Iterable<Hotel> findAllById(Iterable<Long> longs) {
        return hotelFilterRepo.findAllById(longs);
    }

    @Override
    public long count() {
        return hotelFilterRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public void delete(Hotel entity) {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public void deleteAll(Iterable<? extends Hotel> entities) {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public void deleteAll() {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public ArrayList<Hotel> listHotelByStandardAndPropertyAndOrderByStandard(int standard, String property, String location, String orderBy, Pageable pageable) {
        if (orderBy != null && !orderBy.isEmpty())
            return hotelFilterRepo.listHotelByStandardAndPropertyAndOrderByStandard(standard, property, location, orderBy, pageable);
        return hotelFilterRepo.listHotelByStandardAndProperty(standard, property, location, pageable);

    }

    @Override
    public ArrayList<Hotel> listHotelByLocation(String location, Pageable pageable) {
        return hotelFilterRepo.listHotelByLocationAndPageable(location, pageable);
    }

    @Override
    public ArrayList<Hotel> listHotelByPropery(String location,String Property, Pageable pageable) {
        return hotelFilterRepo.listHotelByLocationAndPropertyAndPageable(location,Property,pageable);
    }

    @Override
    public ArrayList<Hotel> listHotelByStandard(String location,int standard, Pageable pageable) {
        if(standard==0){
            return hotelFilterRepo.listHotelByLocationAndPageable(location, pageable);
        }
        return hotelFilterRepo.listHotelByLocationAndStandardAndPageable(location,standard,pageable);
    }

    @Override
    public ArrayList<Hotel> listHotelByStandardAndPropertyAndOrderByStandardAndNoPageable(int standard, String property, String location, String orderBy) {
        if (orderBy != null && !orderBy.isEmpty())
            return hotelFilterRepo.listHotelByStandardAndPropertyAndOrderByStandardAndNoPageable(standard, property, location, orderBy);
        return hotelFilterRepo.listHotelByStandardAndPropertyAndNoPageable(standard, property, location);

    }

    @Override
    public ArrayList<Hotel> listHotelByLocationAndNoPageable(String location) {
        return hotelFilterRepo.listHotelByLocationAndNoPageable(location);
    }

    @Override
    public ArrayList<Hotel> listHotelByProperyAndNoPageable(String location, String Property) {
        return hotelFilterRepo.listHotelByLocationAndPropertyAndNoPageable(location,Property);
    }

    @Override
    public ArrayList<Hotel> listHotelByStandardAndNoPageable(String location, int standard) {
        if(standard==0){
            return hotelFilterRepo.listHotelByLocationAndNoPageable(location);
        }
        return hotelFilterRepo.listHotelByLocationAndStandardAndNoPageable(location,standard);
    }


    // phan dung code-end
}
