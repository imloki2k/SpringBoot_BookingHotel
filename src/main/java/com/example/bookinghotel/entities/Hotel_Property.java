
package com.example.bookinghotel.entities;

        import javax.persistence.*;
        import java.util.List;

@Entity
@Table(name = "hotel_property")
public class Hotel_Property {
    // PHAN NAY CHI PHUC VU MUC DICH TIM HOTEL - AI CODE PHAN NAO LIEN QUAN "CO THE"
    // TAO 1 FILE KHAC DE TRANH CONFLICT
    // phan dung code
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Boolean status;

    public Hotel_Property() {
    }

    public Hotel_Property(String type, Boolean status) {
        this.type = type;
        this.status = status;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "hotel_property")
    private List<Hotel> hotels;

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    // phan dung code-end
}
