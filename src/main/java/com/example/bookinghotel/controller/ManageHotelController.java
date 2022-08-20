package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.repositories.HotelRepository;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ManageHotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/manageHotels")
    public String manageHotel(Model model) {
        //model.addAttribute("hotels", hotelService.findAll());
        return "Pages/index";
    }


    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping("/homepageHotel")
    public String homepageHotel(Model model) {
        model.addAttribute("hotels", hotelService.findAllHotelByUserId(userService.findByUserName(getPrincipal()).getId()));
        // model.addAttribute("hotels",hotelService.findAll());
        return "Pages/hotelManage/all-hotel";
    }

    @GetMapping("/getAllHotel")
    public String getAllHotel(Model model, @RequestParam(name = "nameHotel", required = false) String nameHotel) {
        Iterable<Hotel> hotelList = null;
        if (StringUtils.hasText(nameHotel)) {
            hotelList = hotelRepository.findByAllHotelWithName(nameHotel);
            model.addAttribute("nameHotel", nameHotel);
        } else {
            hotelList = hotelService.findAll();
        }
        model.addAttribute("hotels", hotelList);
        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));
        return "Pages/hotelManage/all-hotel-admin";
    }


    @GetMapping("/showFormCreHotel")
    public String showFormCreateHotel(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "Pages/hotelManage/form-add-hotel";
    }

    @GetMapping("/ShowDetailsOfHotelOfAdmin")
    public String ShowDetailsOfHotelOfAdmin(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));
        return "Pages/hotelManage/formCreHotelForAdmin";
    }

    @PostMapping("/CreateHotelOfAdmin")
    public String CreateHotelOfAdmin(Model model, @ModelAttribute Hotel hotel) {
        Iterable<Hotel> hotelList = null;
        System.out.println(hotel.getId());
        System.out.println(hotel.getNameOfHotel());
        System.out.println(hotel.getAddressOfHotel());
        hotel.setUser(userService.findByUserName(getPrincipal()));
        hotelService.save(hotel);
        hotelList = hotelService.findAll();
        model.addAttribute("hotels", hotelList);
        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));
        return "Pages/hotelManage/all-hotel-admin";
    }

    @PostMapping("/createHotel")
    public String showform(Model model, @ModelAttribute Hotel hotel) {
        System.out.println(hotel.getId());
        System.out.println(hotel.getNameOfHotel());
        System.out.println(hotel.getAddressOfHotel());
        hotel.setUser(userService.findByUserName(getPrincipal()));
        hotelService.save(hotel);
//        model.addAttribute("listHotel", hotelService.findAll());


        return "redirect:/homepageHotel";
    }


    @GetMapping("/findOne/{id}")
    public String findHotelById(@PathVariable("id") long id, Model model) {
        System.out.println("vao find one");
        model.addAttribute("hotel", hotelService.findById(id).get());
        return "Pages/hotelManage/form-edit-hotel";
    }

    @PostMapping("/saveEdit")
    public String updateHotel(@ModelAttribute Hotel hotel) {
        Optional<Hotel> oldHotel = hotelService.findById(hotel.getId());
        System.out.println("vao saveedit: " + hotel.getAddressOfHotel());
        System.out.println(oldHotel.get().getAddressOfHotel());
        oldHotel.get().setAddressOfHotel(hotel.getAddressOfHotel());
        oldHotel.get().setNameOfHotel(hotel.getNameOfHotel());
        oldHotel.get().setStatus(hotel.getStatus());
        oldHotel.get().setId(hotel.getId());
        hotelService.save(hotel);

        return "redirect:/homepageHotel";
    }

    @GetMapping("/delete")
    public String deleteHotel(long id) {
        hotelService.delete(id);
        return "redirect:/homepageHotel";
    }


}
