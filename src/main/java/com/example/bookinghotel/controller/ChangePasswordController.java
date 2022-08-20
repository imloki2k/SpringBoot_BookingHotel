package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.repositories.UserRepository;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Controller
public class ChangePasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private UserInfo userInfo;
    private UserInfo oldUserInfo;

    @GetMapping("/ChangePassword")
    public String goToChangePassword(HttpServletRequest request, Model model) {
        // id=5
        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));
        return "Pages/modal-user/ChangePassWord";

    }

    @PostMapping("/saveChangePasword")
    public String saveChangePasword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Model model, @ModelAttribute UserInfo userInfo) {
        UserInfo user = null;
        try{
            user = userService.findByUserName(this.getPrincipal());
           boolean verifyPassword= UserInfo.getPasswordEncoder().matches(oldPassword,user.getPassword());
            System.out.println("Class: ChangePasswordController | Method: saveChangePasword | verifyPassword:"+verifyPassword);
            System.out.println("Class: ChangePasswordController | Method: saveChangePasword | user.getPassword():"+user.getPassword());
            System.out.println("Class: ChangePasswordController | Method: saveChangePasword | oldPassword:"+oldPassword);

            if (verifyPassword) {
                user.setPassword(newPassword);
                userService.save(user);
                model.addAttribute("statusChangePassWord", "Thay đổi mật khẩu thành công !!!");
            }else{
                model.addAttribute("statusChangePassWord", "Mật khẩu cũ không đúng !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("statusChangePassWord", "e.printStackTrace()");
        }
        System.out.println("Class: ChangePasswordController | Method: saveChangePasword | statusChangePassWord:"+model.getAttribute("statusChangePassWord").toString());

        return "Pages/modal-user/ChangePassWord";
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
