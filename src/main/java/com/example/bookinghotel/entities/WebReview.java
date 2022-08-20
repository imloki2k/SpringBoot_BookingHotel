
package com.example.bookinghotel.entities;

        import javax.persistence.*;
@Entity
@Table(name = "web_review")
//@NamedNativeQuery(name = "getTop4ReviewByRate",query = "select * from web_review order by " +
//        "rate desc limit 4 ",resultClass = WebReview.class)
public class WebReview {
    // CAI NAY CHI DE HIEN THI REVIEW VE WEBSITE O HOMEPAGE, AI CODE CAI GI LIEN QUAN THI "CO THE"
    // TẠO 1 FILE TUONG TU DE KHONG BI CONFLICT
    // phan dung code
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id ;
    private String comment;
    private Long rate ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private UserInfo user;
    public WebReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public WebReview(String comment, Long rate) {
        this.comment = comment;
        this.rate = rate;
    }
    //phan dung code-end
}
