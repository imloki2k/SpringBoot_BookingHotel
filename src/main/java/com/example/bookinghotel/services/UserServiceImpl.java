package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.entities.UserPrinciple;
import com.example.bookinghotel.repositories.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final MessageSource messageSource;

    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, MessageSource messageSource, RoleService roleService) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.roleService = roleService;
    }

    @Override
    public Iterable<UserInfo> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return userRepository.findById(id);
    }

//    @Override
//    public Optional<UserInfo> findByNameAndPassword(String name, String password) {
//        return userRepository.findByNameAndPassword(name, password);
//    }
//
    @Override
    public UserInfo findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserInfo findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<UserInfo> existsByUsernameAndPassword(String email, String password) throws Exception {
        return userRepository.existsByUsernameAndPassword(email,password);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserInfo save(UserInfo user) throws Exception {
//        if (this.existsByUsername(user.getUsername())) {
//            throw new RuntimeException(messageSource.getMessage("validators.username.exists", new Object[] {user.getUsername()}, Locale.getDefault()));
//        }
//        user.setName(user.getUsername());
//        Set<Role> roles = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            if (role.getName().equals("admin")) {
//                Optional<Role> adminRole = roleService.findByName("ROLE_ADMIN");
//                adminRole.ifPresent(roles::add);
//            } else {
//                Optional<Role> userRole = roleService.findByName("ROLE_USER");
//                userRole.ifPresent(roles::add);
//            }
//        });
//        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public boolean changePassword(UserInfo user) throws Exception{
        return true;

    }

    @Override
    public UserInfo updateInfor(UserInfo user) throws Exception{
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setGender(user.getGender());
//        Set<Role> roles = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            if (role.getName().equals("admin")) {
//                Optional<Role> adminRole = roleService.findByName("ROLE_ADMIN");
//                adminRole.ifPresent(roles::add);
//            } else {
//                Optional<Role> userRole = roleService.findByName("ROLE_USER");
//                userRole.ifPresent(roles::add);
//            }
//        });
//        user.setRoles(roles);
        user.setAddress(user.getAddress());
        return userRepository.save(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserInfo user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("vao ham load");
        UserInfo user = userRepository.findByUsername(username);
        System.out.println(user.getPassword());
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.built(user);


    }


}
