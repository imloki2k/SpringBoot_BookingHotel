package com.example.bookinghotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class SearchHotelByRegionController {
    // phan dung code
    @GetMapping("/all-locations")
    String searchHotelByRegion(){
        return "Pages/all-locations";
    }

    @GetMapping("/getAllRegions")
    void returnRegion(HttpServletResponse response){
       String regions = "A,An Giang,B,Bà Rịa - Vũng Tàu,Bạc Liêu,Bắc Giang,Bắc Kạn,Bắc Ninh,Bến Tre,Bình Dương,Bình Định,Bình Phước,Bình Thuận," +
               "C,Cà Mau,Cao Bằng,Cần Thơ,Đ,Đà Nẵng,Đắk Lắk,Đắk Nông,Điện Biên,Đồng Nai,Đồng Tháp,G,Gia Lai," +
               "H,Hà Giang,Hà Nam,Hà Nội,Hà Tĩnh,Hải Dương,Hải Phòng,Hậu Giang,Hòa Bình,Hưng Yên,K,Kiên Giang,Kon Tum,Khánh Hòa," +
               "L,Lai Châu,Lạng Sơn,Lào Cai,Lâm Đồng,Long An,N,Nam Định,Ninh Bình,Ninh Thuận,Nghệ An,P,Phú Thọ,Phú Yên," +
               "Q,Quảng Bình,Quảng Nam,Quảng Ninh,Quảng Ngãi,Quảng Trị,S,Sóc Trăng,Sơn La," +
               "T,Thành phố Hồ Chí Minh,Tây Ninh,Tiền Giang,Tuyên Quang,Thái Bình,Thái Nguyên,Thanh Hóa,Thừa Thiên Huế,Trà Vinh,V," +
               "Vĩnh Long,Vĩnh Phúc,Y,Yên Bái" ;
        // tra ve cac vung mien
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.write(regions);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // phan dung code-end

}
