package com.example.bookinghotel.entities;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class ImageUpload implements Serializable {
    private String image;
    private MultipartFile[] imageSource;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile[] getImageSource() {
        return imageSource;
    }

    public void setImageSource(MultipartFile[] imageSource) {
        this.imageSource = imageSource;
    }
}
