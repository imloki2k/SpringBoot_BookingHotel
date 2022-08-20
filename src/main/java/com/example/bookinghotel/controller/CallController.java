package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.services.UserService;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@Controller
public class CallController {
    //phan dung code
    @Autowired
    UserService userService;

    private final static String Account_Sid = "ACd0868f372cd82561aa813505a4c71892";
    private final static String Auth_id = "29f232367f" +
            "51168a57ee" +
            "c7102e677983";
      // dung khi trang web da duoc deploy tren 1 ten mien cu the
//    @RequestMapping(value = "/voice-note", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
//    public ResponseEntity<Object> getVoiceNote() {
//        String newPws = "1";
//        Say say = new Say.Builder("Your password changed to :" + newPws).voice(Say.Voice.WOMAN)
//                .language(Say.Language.EN_US).build();
//        VoiceResponse response = new VoiceResponse.Builder().say(say).build();
//        return new ResponseEntity<>(response.toXml(), HttpStatus.OK);
//    }

    // thuc hien cuoc goi va thong bao mat khau moi voi nguoi dung
    @RequestMapping(value = "/call", method = RequestMethod.POST)
    public ResponseEntity<Object> makeCall(@RequestParam("phone") String phone,@RequestParam("username") String username) {

        String newPassword = "1";
        try {
            changePassword(username,newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TwilioRestClient client = new TwilioRestClient.Builder(Account_Sid, Auth_id).build();
        PhoneNumber to = new PhoneNumber(phone);
        PhoneNumber from = new PhoneNumber("+18456825828");
//        Twilio.init(Account_Sid, Auth_id);
//        https://drive.google.com/uc?export=view&id=1e5k9uUey0OuJ4VOAzZMePxKipyZVbR9g
//        http://demo.twilio.com/docs/voice.xml

        //validate number - khong ho tro tai khoan trial
//
//        ValidationRequest validationRequest = ValidationRequest.creator(
//                        new com.twilio.type.PhoneNumber(phone))
//                .setFriendlyName(phone)
//                .create();
        // tao client noi chuyen voi khach hang cach 1
        //duong dan file xml chua giong noi
        URI uri = URI.create("https://drive.google.com/uc?export=open&id=1e5k9uUey0OuJ4VOAzZMePxKipyZVbR9g");
        com.twilio.rest.api.v2010.account.Call call = com.twilio.rest.api.v2010.account.Call
                .creator(to,from,uri).create(client);
        // tao client noi chuyen voi khach hang - cach 2
//        com.twilio.rest.api.v2010.account.Call call2 = Call.creator(
//                        to,
//                        from,
//                        new com.twilio.type.Twiml("<Response><Say>Your password changed to "+newPassword+"" +
//                                ".Please change your password soon</Say></Response>"))
//                .create();
        System.out.println("Cuoc goi da duoc thuc hien");
        return new ResponseEntity<Object>("Cuoc goi da duoc thuc hien", HttpStatus.OK);

    }
    // thuc hien doi mat khau
    public void changePassword(String username,String password) throws Exception {
        UserInfo user = userService.findByUserName(username);
        user.setPassword(password);
        userService.save(user);
    }
    //phan dung code-end

}


