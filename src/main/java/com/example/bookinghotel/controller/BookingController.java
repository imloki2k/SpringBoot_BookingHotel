package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Booking;
import com.example.bookinghotel.repositories.BookingRepository;
import com.example.bookinghotel.services.BookingService;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

//    @GetMapping("/saveBooking")
//    public String saveBooking(@RequestParam("checkin") String checkin,
//                              @RequestParam("checkout") String checkout,
//                              @RequestParam("numberOfRoom") String numberOfRoom,
//                              @RequestParam("info-name") String name,
//                              @RequestParam("info-phone") String phone,
//                              @RequestParam("info-email") String email
//                              ){
//        System.out.println("vao controller");
//        System.out.println(checkin);
//        System.out.println(checkout);
//        System.out.println(numberOfRoom);
//        System.out.println(name);
//        System.out.println(phone);
//        System.out.println(email);
//        return "redirect:/";
//    }


    @GetMapping("/listBooking")
    public String listBooking(Model model,
                              @RequestParam(name = "start_date", required = false) String start_date,
                              @RequestParam(name = "end_date", required = false) String end_date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("ngay bat dau: " + start_date);
        System.out.println("ngay ket thuc: " + end_date);
        Iterable<Booking> bookingList = null;
        if (StringUtils.hasText(start_date) && StringUtils.hasText(end_date)) {
            bookingList = bookingRepository.findByStartDateAndEndDate(hotelService.findAllHotelByUserId(userService.findByUserName(this.getPrincipal()).getId()).iterator().next().getId(), LocalDate.parse(start_date, formatter), LocalDate.parse(end_date, formatter));
        } else if (StringUtils.hasText(start_date)) {
            bookingList = bookingRepository.findByStartDate(hotelService.findAllHotelByUserId(userService.findByUserName(this.getPrincipal()).getId()).iterator().next().getId(), LocalDate.parse(start_date, formatter));
        } else if (StringUtils.hasText(end_date)) {
            bookingList = bookingRepository.findByEndDate(hotelService.findAllHotelByUserId(userService.findByUserName(this.getPrincipal()).getId()).iterator().next().getId(), LocalDate.parse(end_date, formatter));
        } else {
            bookingList = bookingRepository.findAllBookingByHotelId(hotelService.findAllHotelByUserId(userService.findByUserName(this.getPrincipal()).getId()).iterator().next().getId());
        }
        model.addAttribute("bookingList", bookingList);

        return "/Pages/Bookings/booking_list";
    }

    @GetMapping("/listBookingOfAdmin")
    public String listBookingOfAdmin(Model model,
                                     @RequestParam(name = "start_date", required = false) String start_date,
                                     @RequestParam(name = "end_date", required = false) String end_date ,@RequestParam(name = "name", required = false) String userName) {

        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String start_date1=LocalDate.parse(start_date,formatter).toString();
//        String end_date1=LocalDate.parse(start_date,formatter).toString();
        System.out.println("ngay bat dau: " + start_date);
        System.out.println("ngay ket thuc: " + end_date);
        Iterable<Booking> bookingList = null;
        System.out.println("Class: BookingController | Method: listAllBookingOfUser | ID User:" + userService.findByUserName(this.getPrincipal()).getId());
        if(StringUtils.hasText(userName)){
            model.addAttribute("name", userName);
            if (StringUtils.hasText(start_date) && StringUtils.hasText(end_date)) {
                bookingList = bookingRepository.findAllBookingAdminAndStartDateEndDateAndNameUser(LocalDate.parse(start_date, formatter), LocalDate.parse(end_date, formatter),userName);

            } else if (StringUtils.hasText(start_date)) {
                bookingList = bookingRepository.findAllBookingByAdminAndStartDateAndNameUser(LocalDate.parse(start_date, formatter),userName);

            } else if (StringUtils.hasText(end_date)) {
                bookingList = bookingRepository.findAllBookingByAdminAndEndDateAndNameUser(LocalDate.parse(end_date, formatter),userName);

            } else {
                bookingList = bookingRepository.findAllBookingAdminAndNameUser(userName);
            }
        }else{
            if (StringUtils.hasText(start_date) && StringUtils.hasText(end_date)) {
                bookingList = bookingRepository.findAllBookingAdminAndStartDateEndDate(LocalDate.parse(start_date, formatter), LocalDate.parse(end_date, formatter));

            } else if (StringUtils.hasText(start_date)) {
                bookingList = bookingRepository.findAllBookingByAdminAndStartDate(LocalDate.parse(start_date, formatter));

            } else if (StringUtils.hasText(end_date)) {
                bookingList = bookingRepository.findAllBookingByAdminAndEndDate(LocalDate.parse(end_date, formatter));

            } else {
                bookingList = bookingRepository.findAllBookingAdmin();
            }
        }

        model.addAttribute("bookingList", bookingList);
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);

        return "/Pages/Bookings/booking_list_of_admin";
    }

    @GetMapping("/listAllBookingOfCustomer")
    public String listAllBookingOfCustomer(Model model,
                                           @RequestParam(name = "start_date", required = false) String start_date,
                                           @RequestParam(name = "end_date", required = false) String end_date) {
        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String start_date1=LocalDate.parse(start_date,formatter).toString();
//        String end_date1=LocalDate.parse(start_date,formatter).toString();
        System.out.println("ngay bat dau: " + start_date);
        System.out.println("ngay ket thuc: " + end_date);
        Iterable<Booking> bookingList = null;
        Long id = Long.parseLong(userService.findByUserName(this.getPrincipal()).getId().toString());
        System.out.println("Class: BookingController | Method: listAllBookingOfUser | ID User:" + userService.findByUserName(this.getPrincipal()).getId());
        if (StringUtils.hasText(start_date) && StringUtils.hasText(end_date)) {
            bookingList = bookingRepository.findAllBookingByUserIdAndStartDateEndDate(id, LocalDate.parse(start_date, formatter), LocalDate.parse(end_date, formatter));

        } else if (StringUtils.hasText(start_date)) {
            bookingList = bookingRepository.findAllBookingByUserIdAndStartDate(id, LocalDate.parse(start_date, formatter));

        } else if (StringUtils.hasText(end_date)) {
            bookingList = bookingRepository.findAllBookingByUserIdAndEndDate(id, LocalDate.parse(end_date, formatter));

        } else {
            bookingList = bookingRepository.findAllBookingByUserId(id);
        }

        model.addAttribute("bookingList", bookingList);
        model.addAttribute("start_date", start_date);
        model.addAttribute("end_date", end_date);

        return "/Pages/Bookings/booking_list_of_custommer";
    }
    @GetMapping("/deleteBookingOfCustomer")
    public String deleteBookingOfCustomer(Long id,Model model,
                                          @RequestParam(name = "start_date", required = false) String start_date,
                                          @RequestParam(name = "end_date", required = false) String end_date) {
        System.out.println("Class: BookingController | Method: deleteBookingOfCustomer | ID Booking:" + id);
        bookingRepository.findById(id).get().setStatus(0);
        bookingRepository.save(bookingRepository.findById(id).get());
        return listAllBookingOfCustomer(model, start_date, end_date);
    }
    @GetMapping("/deleteBooking")
    public String deleteBooking(Long id) {
        bookingRepository.findById(id).get().setStatus(0);
        bookingRepository.save(bookingRepository.findById(id).get());
        return "redirect:/listBooking";
    }


    @GetMapping("/addBooking")
    public String addBooking() {
        return "/Pages/Bookings/add_booking_list";
    }


    @GetMapping("/editBooking")
    public String editBooking() {
        return "/Pages/Bookings/edit_booking_list";
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
}
