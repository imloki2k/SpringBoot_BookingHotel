package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.services.EmailService;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Optional;
import java.util.Random;

@Controller
public class PasswordRecoveryController {
    // phan dung code
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;


    @GetMapping("/passwordForget")
    public String findUserF(){
        return "Pages/modal-user/user-resetpassword-finduser";
    }

    // tìm tài khoản cần đổi pass
    @PostMapping("/findUser")
    public void findUser(@RequestParam("username") String username, HttpServletResponse response){
        try(PrintWriter out = response.getWriter()){

            Optional<UserInfo> user = Optional.ofNullable(userService.findByUserName(username));
            if(user.isPresent())
                out.write("found");
            else
                out.write("cant find");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //trả về các method khôi phục tài khoản khả dụng
    @GetMapping("/recoverPassword/{username}")
    public String recoverMethods(@PathVariable(value="username")String username, Model model){
        Optional<UserInfo> user = Optional.ofNullable(userService.findByUserName(username));
        UserInfo userInfo ;
        if(user.isPresent()) {
            userInfo = user.get();
            model.addAttribute("username",userInfo.getUsername());
            model.addAttribute("mail",userInfo.getEmail());
            model.addAttribute("phone", "+84"+userInfo.getPhoneNumber().substring(1,10));
            return "Pages/modal-user/user-resetpassword-methods";
        }
        else{
            model.addAttribute("Message","No Email attached");
            return "Pages/modal-user/user-resetpassword-finduser";
        }


    }
    // mở form đổi pass
    @GetMapping("/resetPasswordF/{passwordToken}")
    public String resetPasswordF(@PathVariable(value="passwordToken") String passwordToken,Model model,HttpServletResponse response){
        // đọ tất cả tên tài khoản với token - giải pháp tạm thời

        Iterable<UserInfo> users = userService.findAll();
        UserInfo recoveredUser = null;
        for (UserInfo user:users)
        {
            if(user.getPasswordToken()!=null)
                if(user.getPasswordToken().equals(passwordToken)){
                    recoveredUser = user;
                    model.addAttribute("name", user.getName());// name gọi sau khi đăng nhập
                    model.addAttribute("username", user.getUsername());
                    break;
                }
        }
        if(recoveredUser==null) return "redirect:/";
        return "Pages/modal-user/user-resetpassword-changepassword";
    }
    // đổi pass
    @PostMapping("/resetPassword")
    public void resetPassword(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletResponse response) throws Exception {
        UserInfo user = userService.findByUserName(username);
        user.setPassword(password);
        user.setPasswordToken("");
        userService.save(user);
        try(PrintWriter out = response.getWriter()){

            out.write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // gửi gmail hỗ trợ đổi pass
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestParam("username")String username,@RequestParam("email")String email,HttpServletResponse response) throws Exception {
        UserInfo user = userService.findByUserName(username);

        user.setPasswordToken(createPwsToken());

        userService.save(user);
        emailService.sendAHTMLMessage(email,"Hỗ trợ lấy lại mật khẩu","http://localhost:8080/resetPasswordF/"+user.getPasswordToken(),user.getName());
        try(PrintWriter out = response.getWriter()){

            out.write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String createPwsToken(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
    @GetMapping("/callMessage")
    String getCallMessage(){
        return "Pages/modal-user/callMessage";
    }
    // phan dung code-end
}
