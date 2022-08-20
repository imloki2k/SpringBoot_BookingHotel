package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.services.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class SendMessageController {
    @Autowired
    UserService userService;

    public static final String ACCOUNT_SID = "ACd0868f372cd82561aa813505a4c71892";
    public static final String AUTH_TOKEN = "29f232367f" +
            "51168a57ee" +
            "c7102e677983";
    @PostMapping("/sendMessage")
    public ResponseEntity<Object> sendMessagePwsRecovery(@RequestParam("phone") String phone, @RequestParam("username") String username) throws Exception {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(phone),
                        new com.twilio.type.PhoneNumber("+18456825828"),
                        "\nYOY Booking hotel:\nHi "+username+". \nYour password changed to "+changePassword(username))
                .create();
        return new ResponseEntity<Object>("tin nhan da duoc gui di", HttpStatus.OK);

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
    public String changePassword(String username) throws Exception {
        String newPass = createPwsToken();
        UserInfo user = userService.findByUserName(username);
        user.setPassword(newPass);
        userService.save(user);
        return newPass;
    }

}
