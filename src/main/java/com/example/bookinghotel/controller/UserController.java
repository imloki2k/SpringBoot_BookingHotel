package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.services.RoleService;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String showForm(Model model){
        System.out.println("vao sign up");
        model.addAttribute("user", new UserInfo());
//        return "/greeting";
       return "/Pages/modal-user/user-signup";
    }

    @PostMapping("/register")
    public String registerUser(@Validated @ModelAttribute("user") UserInfo user , BindingResult result , RedirectAttributes redirect, Model model) throws Exception {

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.save(roleUser);
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);

        user.setRoles(roles);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setToken("user");
        user.setActive(true);
        user.setName(user.getUsername());

        if (result.hasErrors()) {
            if(userService.findByUserName(user.getUsername()) != null){
                model.addAttribute("errolUsername", "Username was existed");
            }
            if(userService.findByEmail(user.getEmail()) != null) {
                model.addAttribute("errolEmail", "Email was existed");
            }
            return "/Pages/modal-user/user-signup";
        }
        else if(userService.findByUserName(user.getUsername()) != null){
            model.addAttribute("errolUsername", "Username was existed");
            if(userService.findByEmail(user.getEmail()) != null) {
                model.addAttribute("errolEmail", "Email was existed");
            }
            return "/Pages/modal-user/user-signup";
        }
        else if(userService.findByEmail(user.getEmail()) != null){
            model.addAttribute("errolEmail", "Email was existed");
            return "/Pages/modal-user/user-signup";
        }
        else {
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
            redirect.addFlashAttribute("globalMessage", "Register successfully.");
                userService.save(user);
                return "redirect:/signup";

            }
    }

}
