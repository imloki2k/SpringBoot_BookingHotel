package com.example.bookinghotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {


    @GetMapping("/login")
    public String login(){
        System.out.println("vaologin");

        return "/Pages/booker/login";
    }

    @PostMapping("/fail_login")
    public String handeLoginFail(Model model){
        System.out.println("vao ham fail");
        model.addAttribute("errol", "Invalid username or password");
        return "/Pages/booker/login";
    }

    @GetMapping("/logout")
    public String logout(){return "/";}

}
