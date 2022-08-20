
package com.example.bookinghotel.entities;

        import javax.persistence.*;

@Entity
@Table(name = "hotel_preview_image")
public class Hotel_Preview_Image {
    // PHAN NAY CHI DE HO TRO TRANG DISPLAY HOTEL , AI CODE CAI GI KHAC CO THE COPY VA TAO 1 FILE TUONG TU
    // DE TRANH CONFLICT
    // phan dung code
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String image;

    public Hotel_Preview_Image() {
    }

    public Hotel_Preview_Image(String image, Hotel hotel) {
        this.image = image;
        this.hotel = hotel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel ;
    // phan dung code - end
}
