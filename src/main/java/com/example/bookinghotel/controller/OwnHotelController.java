package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.*;
import com.example.bookinghotel.repositories.RoomRepository;
import com.example.bookinghotel.repositories.UserRepository;
import com.example.bookinghotel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class OwnHotelController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HomeService homeService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private PropertyTypeService typeService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private HomeService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signupOwn")
    public String showFormRegisOfOwner(Model model) {
        model.addAttribute("owner", new UserInfo());
        return "Pages/owner/formOwnRegister";

    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @Value("C:/ListRoom/file/")
    private String fileUpload;

    @PostMapping("/saveOwner")
    public String saveInforOfOwner(@Validated @ModelAttribute("owner") UserInfo user , BindingResult result , RedirectAttributes redirect, Model model) throws Exception {

        Role roleOwner = new Role();
        roleOwner.setName("ROLE_OWNER");
//        Role roleUser = new Role();
//        roleUser.setName("ROLE_USER");
        roleService.save(roleOwner);
//        roleService.save(roleUser);
        Set<Role> roles = new HashSet<>();
        roles.add(roleOwner);
//        roles.add(roleUser);

        user.setRoles(roles);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

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
            return "/Pages/owner/formOwnRegister";
        }
        else if(userService.findByUserName(user.getUsername()) != null){
            model.addAttribute("errolUsername", "Username was existed");
            if(userService.findByEmail(user.getEmail()) != null) {
                model.addAttribute("errolEmail", "Email was existed");
            }
            return "/Pages/owner/formOwnRegister";
        }
        else if(userService.findByEmail(user.getEmail()) != null){
            model.addAttribute("errolEmail", "Email was existed");
            return "/Pages/owner/formOwnRegister";
        }
        else {
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
            redirect.addFlashAttribute("globalMessage", "Register successfully.");
            userService.save(user);
            return "redirect:/signupOwn";

        }

    }


    public Long idHotel;


    @GetMapping("/manageRoom/{id}")
    public String homepageRoom(@PathVariable("id") Long id , Model model){
        idHotel = id;
//        model.addAttribute("rooms",roomService.findAllByHotelId(id));
        return "redirect:/roomHomepage";
    }

    @GetMapping("/roomHomepage")
    public String homepageRoom(Model model){
        model.addAttribute("rooms",roomService.findAllByHotelId(idHotel));
        for (Room r:roomService.findAllByHotelId(idHotel)) {
            System.out.println(r.getPropertyType().getName());
        }
        return "Pages/roomManage/all-room";
    }

    @GetMapping("/getAllRoomOfRoleAdmin")
    public String getAllRoomOfRoleAdmin(Model model , @RequestParam(name = "nameHotel", required = false) String nameHotel){
        Iterable<Room> roomList = null;
        if(StringUtils.hasText(nameHotel)){
            roomList=roomRepository.findAllRoomWithAdminWithNameHotel(nameHotel);
            model.addAttribute("nameHotel",nameHotel);
        }
        else{
            roomList=roomRepository.findAllRoomWithAdmin();
        }

        model.addAttribute("rooms",roomList);
        return "Pages/roomManage/all_room_admin";
    }

    @GetMapping("/createRoom")
    public String showFormCreateRoom( Model model){
        model.addAttribute("listProperty", typeService.getAll());
        Room room = new Room();
        room.setHotel(hotelService.findById(idHotel).get());
        model.addAttribute("room", room);
        System.out.println("name of hote : "+hotelService.findById(idHotel).get().getAddressOfHotel());
        return "Pages/roomManage/add-room";
    }

    @PostMapping("/saveRoom")
    public String saveRoom(Model model, @ModelAttribute("room") Room room,@RequestParam("pr") Long id,RedirectAttributes redirect){
        System.out.println("vao save room");
        System.out.println("id property"+id);
        System.out.println("id saveroom hotel la: " +idHotel);
        room.setHotel(hotelService.findById(idHotel).get());
        System.out.println("name of hote : "+room.getHotel().getNameOfHotel());
       System.out.println("Tên cua type la: "+ typeService.getOne(id).get().getName());
        System.out.println("Tên cua type la: "+ room.getStatus());

        room.setPropertyType(typeService.getOne(id).get());
        MultipartFile multipartFile = room.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(room.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        room.setImgSrc3(fileName);

        room.setBookings(null);
        room.setDiscount(discountService.findById(1L).get());
        room.setUser(userService.findByUserName(getPrincipal()));
        homeService.save(room);
        model.addAttribute("rooms",roomService.findAllByHotelId(idHotel));


        return "Pages/roomManage/all-room";
    }

    @GetMapping("/findOneRoom/{id}")
    public String findRoomById(@PathVariable("id") long id , Model model){
        model.addAttribute("room", roomService.findById(id).get());
        model.addAttribute("listProperty", typeService.getAll());
        return "Pages/roomManage/edit-room";
    }

    @PostMapping("/saveEditRoom")
    public String updateRoom(@ModelAttribute Room room){

        MultipartFile multipartFile = room.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(room.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        room.setImgSrc3(fileName);
        roomService.save(room);


        return "redirect:/roomHomepage";
    }

    @GetMapping("/deleteRoom")
    public String deleteRoomById(long id){
        roomService.deleteById(id);
        return "redirect:/roomHomepage";
    }


    @GetMapping("/hotelOwnerProfile")
    public String editHotelOwnerProfile(HttpServletRequest request,Model model){
        model.addAttribute("hotelOwner",userService.findByUserName(this.getPrincipal()));
        return "Pages/owner/hotelOwnerProfile";
    }

    @PostMapping("/hotelOwnerProfile/save")
    public String saveEditHotelOwnerProfile(@ModelAttribute UserInfo hotelOwner) {

        UserInfo oldHotelOwner = userService.findByUserName(this.getPrincipal());
        oldHotelOwner.setName(hotelOwner.getName());
        oldHotelOwner.setEmail(hotelOwner.getEmail());
        oldHotelOwner.setPhoneNumber(hotelOwner.getPhoneNumber());
        oldHotelOwner.setAddress(hotelOwner.getAddress());
        oldHotelOwner.setGender(hotelOwner.getGender());
        try{
            userService.save(oldHotelOwner);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/hotelOwnerProfile";
    }


    @GetMapping("/OwnerChangePassword")
    public String getOwnerChangePassword(HttpServletRequest request, Model model) {

        model.addAttribute("hotelOwner", userService.findByUserName(this.getPrincipal()));
        return "Pages/owner/owner_changePassword";

    }


    @PostMapping("/saveOwnerChangePassword")
    public String saveOwnerChangePasword(@RequestParam("oldPassword") String oldPassword,
                                         @RequestParam("newPassword") String newPassword,
                                         Model model,
                                         @ModelAttribute UserInfo hotelOwner) {
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

        model.addAttribute("hotelOwner", userService.findByUserName(this.getPrincipal()));
        return "Pages/owner/owner_changePassword";
    }




}
