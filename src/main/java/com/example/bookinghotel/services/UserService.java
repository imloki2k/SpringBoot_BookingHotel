package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Iterable<UserInfo> findAll();

    Optional<UserInfo> findById(Long id);

    UserInfo findByEmail(String email);

    UserInfo save(UserInfo user) throws Exception;

    UserInfo findByUserName(String username);

    boolean existsByUsername(String username);
    
    public UserInfo updateInfor(UserInfo user) throws Exception;

    public boolean changePassword(UserInfo user) throws Exception;

    Optional<UserInfo> existsByUsernameAndPassword(String email, String password) throws Exception;

    void delete(Long id);
}
