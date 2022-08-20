
package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Booking;
import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.entities.Room;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

// chi viet code cho homepage vao day
@Controller
public class SearchHotelHomePageController {
    // phan dung code
    @Autowired
    HotelService hotelService;

    @GetMapping("/search_hotel_by_name")
    String getRoomBooking() {
        return "Pages/search-hotelbynameD";
    }

    @GetMapping("/getAllHotelNames")
    void getNamesOfHotel(HttpServletResponse response) {
        String ans = "";
        Iterable<Hotel> hotels = hotelService.findAll();
        //lay toan bo ten cua hotel de ho tro tim kiem bang js
        for (Hotel hotel : hotels) {
            String formatName = hotel.getNameOfHotel().replace(',','|');
            ans += formatName + ",";
        }
        if (ans.isEmpty()) {
            // thong bao neu loi xay ra khong lay duoc thong tin cua hotel
            ans = "cant get hotel names";
        } else {
            // loai bo dau "," cuoi cung
            ans = ans.substring(0, ans.length() - 2);
        }
        // xu ly van de dau trong data
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.write(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/checkAvailableHotel")
    public void checkAvailableHotel(@RequestParam("hotel_name") String hotel_name,
                                    @RequestParam("start_date") String start_date,
                                    @RequestParam("end_date") String end_date,
                                    @RequestParam("number_of_people") int number_of_people,
                                    @RequestParam("preview_value") int preview_value,
                                    HttpServletResponse response) {
//        System.out.println(hotel_name);
//        System.out.println(start_date);
//        System.out.println(end_date);
//        System.out.println(number_of_people);

        String ans = "error";
        hotel_name = hotel_name.replace('|',',');
        Optional<Hotel> hotel = hotelService.findHotelByName(hotel_name);

        if (hotel.isPresent()) {
            if (preview_value == 0) {
                boolean checkValidateDateAndBed = checkValidateDateAndBed(start_date, end_date, number_of_people, hotel.get());

                if (checkValidateDateAndBed == true) ans = hotel.get().getId() + "";
                else ans = "error";
            } else {
                ans = hotel.get().getId() + "";
            }
            // tim duoc return id


        }
        try (PrintWriter out = response.getWriter()) {
            out.write(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidateDateAndBed(String start_date, String end_date, int number_of_people, Hotel hotel) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dtf = dtf.withLocale(Locale.getDefault());
        LocalDate start_dateD = LocalDate.now();
        LocalDate end_dateD = LocalDate.now();
        List<Room> acceptedRooms = new ArrayList<>();
        try {
            start_dateD = LocalDate.parse(start_date);
            end_dateD = LocalDate.parse(end_date);
            List<Room> rooms = hotel.getRooms();
            if (rooms != null) {
                for (int j = 0; j < rooms.size(); j++) {
                    boolean isValid = true;// coi nhu phong dang trong , neu quet booking thay trung ngay se set ve false

                    List<Booking> bookings = rooms.get(j).getBookings();
                    if (bookings != null) {
                        for (int k = 0; k < bookings.size(); k++) {
                            if (bookings.get(k).getStartDate().isAfter(end_dateD) || bookings.get(k).getEndDate().isBefore(start_dateD)) {
                                // booking hien tai dap ung yeu cau khong thay doi gia tri valid
                            } else {
                                isValid = false;//khong dap ung yeu cau
                                break;
                            }
                        }
                    }
                    if (isValid == true) {
                        if (rooms.get(j).getTotalOfBedroom() == number_of_people)
                            acceptedRooms.add(rooms.get(j));
                    }
                    System.out.println(j);
              }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return acceptedRooms.size() > 0;
    }
    // phan dung code-end

    //lay toan bo ten cua hotel de ho tro tim kiem bang js
}
