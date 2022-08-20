package com.example.bookinghotel.controller;
import com.example.bookinghotel.entities.TermOfUser;
import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.repositories.UserRepository;
import com.example.bookinghotel.services.TermOfUserService;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TermsOfUseController {
    @Autowired
    private UserService userService;
    @Autowired
    private TermOfUserService termOfUserService;
    @Autowired
    private UserRepository userRepository;
    private UserInfo userInfo;
    private UserInfo oldUserInfo;
    private TermOfUser termOfUser;

    @GetMapping("/TermOfUser")
    public String goToTermOfUser(HttpServletRequest request, Model model) {
        // id=5
        System.out.println("Class: TermOfUserService | Method: updateTermOfUser |  " + termOfUserService.findById(1L).get().getDetails());
        System.out.println("Class: TermOfUserService | Method: updateTermOfUser |  " + termOfUserService.findById(1L).get().getId());
        model.addAttribute("termOfUser", termOfUserService.findById(1L).get());
        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));

        return "Pages/modal-user/TermsOfUse";

    }

    @PostMapping("/saveTermOfUser")
    public String updateTermOfUser(@ModelAttribute TermOfUser termOfUser1, Model model) {
        TermOfUser oldTermOfUser= termOfUserService.findById(1L).get();
        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));
        oldTermOfUser.setDetails(termOfUser1.getDetails());
        System.out.println("Class: TermOfUserService | Method: updateTermOfUser |  " + termOfUser1.getDetails());
        try {
            termOfUserService.save(oldTermOfUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Pages/modal-user/TermsOfUse";
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
