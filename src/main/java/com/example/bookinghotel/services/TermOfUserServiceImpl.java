package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.TermOfUser;
import com.example.bookinghotel.repositories.RoleRepository;
import com.example.bookinghotel.repositories.TermOfUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TermOfUserServiceImpl implements TermOfUserService {

    @Autowired
    private TermOfUserRepository termOfUserRepository;

    @Override
    public Optional<TermOfUser> findById(Long id) {
        return termOfUserRepository.findById(id);
    }

    @Override
    public TermOfUser save(TermOfUser termOfUser) {
        return termOfUserRepository.save(termOfUser);
    }

    @Override
    public Iterable<TermOfUser> findAll() {
        return termOfUserRepository.findAll();
    }
}
