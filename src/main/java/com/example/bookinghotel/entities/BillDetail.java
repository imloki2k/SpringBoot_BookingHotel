package com.example.bookinghotel.entities;


import javax.persistence.*;

@Entity
@Table(name = "bill_detail")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long goods_id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false, updatable = false)
    private Bill bill;


    public BillDetail(Long id, Long goods_id, Bill bill) {
        this.id = id;
        this.goods_id = goods_id;
        this.bill = bill;
    }

    public BillDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
