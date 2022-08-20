package com.example.bookinghotel.entities;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "Title")
    private String title;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Column(name = "Script")
    private String script;

    @Column(name = "Description")
    private String description;

    public Blog(Long id, String title, String description, MultipartFile image, String imgSrc, UserInfo user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.imgSrc = imgSrc;
        User = user;
    }

    @Transient
    MultipartFile image;

    @Column(nullable = false)
    private String imgSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false, updatable = false)
    private UserInfo User;

    public Blog() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public UserInfo getUser() {
        return User;
    }

    public void setUser(UserInfo user) {
        User = user;
    }
}
