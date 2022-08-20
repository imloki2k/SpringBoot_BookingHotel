package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Report;
import com.example.bookinghotel.repositories.HotelReportRepository;
import com.example.bookinghotel.services.ReportService;
import com.example.bookinghotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class HotelReportController {

    @Autowired
    private HotelReportRepository hotelReportRepository;
    @Autowired
    private ReportService reportService;
    @Autowired
    private RoomService roomService;


    public Long room_id;

    @GetMapping("/report")
    public String hotelReport(Long id,Model model){
        model.addAttribute("report",new Report());
        model.addAttribute("roomInfo",roomService.findById(id).get());
        room_id = id;
        return "Room/hotel_report";
    }

//    @GetMapping("/report")
//    public String hotelReport(){
//        return "/Room/hotel_report.html";
//    }

    @PostMapping("/saveReport")
    public String saveHotelReport(@ModelAttribute("report") @Valid Report report, BindingResult result, RedirectAttributes redirect, Model model){

        System.out.println("Room id :" + room_id);
        report.setEmail(report.getEmail());
        report.setMessage(report.getMessage());
        report.setPhoneNumber(report.getPhoneNumber());
        report.setName(report.getName());
        report.setHotel(roomService.findById(room_id).get().getHotel());
        report.setRoom(roomService.findById(room_id).get());

        if(result.hasErrors()){
            model.addAttribute("globalMessage", "Gửi không thành công!");
        }else{
            model.addAttribute("globalMessage", "Gửi thành công!");
            reportService.save(report);
        }
//        redirect.addFlashAttribute("globalMessage", "Gửi thành công!");
//        reportService.save(report);
        model.addAttribute("roomInfo", roomService.findById(room_id).get());
        return "Room/hotel_report";
    }


    @GetMapping("/hotelReportList")
    public String hotelReportListOwner(Model model,
                                       @RequestParam(name = "nameOfhotel",required = false) String nameOfhotel,
                                       @RequestParam(name = "message",required = false) String message){

        Iterable<Report> reports = null;
        if(StringUtils.hasText(nameOfhotel) && StringUtils.hasText(message)){
            reports = hotelReportRepository.findByNameOfHotelAndMessage(nameOfhotel,message);
        }else if(StringUtils.hasText(nameOfhotel)){
            reports = hotelReportRepository.findByNameOfHotel(nameOfhotel);
        }else if(StringUtils.hasText(message)){
            reports = hotelReportRepository.findByMessage(message);
        }else{
            reports = hotelReportRepository.findAll();
        }
        model.addAttribute("report",reports);

        return "/Pages/Admin/hotel_report_list";
    }

    @GetMapping("/deleteReport")
    public String deleteHotelReport(long id){
        reportService.delete(id);
        return "redirect:/hotelReportList";
    }


}
