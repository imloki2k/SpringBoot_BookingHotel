package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserInfo, Long> {

    Optional<UserInfo> findByNameAndPassword(String name, String password);

    UserInfo findByEmail(String email);

    UserInfo findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<UserInfo> findById(Long id);

    Iterable<UserInfo> findAll();


    @Query(value = "SELECT * FROM booking_hotelver10.users Where users.name like concat('%',?1,'%') and users.phone_number like concat('%',?2,'%') and users.email like concat('%',?3,'%')", nativeQuery = true)
    Iterable<UserInfo> findByNameAndPhoneNumberAndEmail(String name,String phone, String email);

    @Query(value = "SELECT * FROM booking_hotelver10.users Where users.name like concat('%',?1,'%') and users.phone_number like concat('%',?2,'%')", nativeQuery = true)
    Iterable<UserInfo> findByNameAndPhoneNumber(String name, String phone);

    @Query(value = "SELECT * FROM booking_hotelver10.users Where users.name like concat('%',?1,'%') and users.email like concat('%',?2,'%')", nativeQuery = true)
    Iterable<UserInfo> findByNameAndEmail(String name, String email);

    @Query(value = "SELECT * FROM booking_hotelver10.users Where users.phone_number like concat('%',?1,'%') and users.email like concat('%',?2,'%')", nativeQuery = true)
    Iterable<UserInfo> findByPhoneNumberAndEmail(String phone, String email);

    @Query(value = "SELECT * FROM booking_hotelver10.users Where users.name like concat('%',?1,'%')", nativeQuery = true)
    Iterable<UserInfo> findByNameContaining(String name);

    @Query(value = "SELECT * FROM booking_hotelver10.users Where users.phone_number like concat('%',?1,'%')", nativeQuery = true)
    Iterable<UserInfo> findByPhoneNumberContaining(String phone);

    @Query(value = "SELECT * FROM booking_hotelver10.users Where users.email like concat('%',?1,'%')", nativeQuery = true)
    Iterable<UserInfo> findByEmailContaining(String email);


    @Query(value = "SELECT * FROM booking_hotelver10.users where username=?1", nativeQuery = true)// and password=?2;
    Optional<UserInfo> existsByUsernameAndPassword(String email,String password);
}
