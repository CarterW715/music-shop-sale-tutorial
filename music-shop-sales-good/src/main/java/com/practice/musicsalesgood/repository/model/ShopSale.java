package com.practice.musicsalesgood.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop_sale", schema = "good")
public class ShopSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_sale_id")
    long id;

    @OneToOne(mappedBy = "shopSale", cascade = CascadeType.ALL)
    ShopReturn shopReturn;

    @Column(name = "sale_id")
    UUID saleId;

    @Column(name = "inst_nm")
    String instrument;

    @Column(name = "inst_cd")
    String instrumentCode;

    @Column(name = "manufct_num")
    String manufactureNumber;

    @Column(name = "employee_nm")
    String employeeName;

    @Column(name = "cust_nm")
    String customerName;

    @Column(name = "sale_dt")
    LocalDateTime saleDate;

    @Column(name = "sale_amt")
    Double saleAmount;

    Double tax;

    @Column(name = "promo_cd")
    String promoCode;

    @Column(name = "dscnt_amt")
    Double discountAmount;

    @Column(name = "grnd_ttl")
    Double grandTotal;

    @Column(name = "msg_id")
    UUID messageId;

    @Column(name = "evnt_ts")
    LocalDateTime eventTimestamp;

    @Column(name = "msg_version")
    String messageVersion;

    @Column(name = "shop_id")
    UUID shopId;

    @Column(name = "shop_ctry")
    String shopCountry;

    @Column(name = "shop_st")
    String shopState;

    @Column(name = "shop_nm")
    String shopName;

}
