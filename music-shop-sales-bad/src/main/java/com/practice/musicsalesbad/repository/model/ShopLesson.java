package com.practice.musicsalesbad.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop_lesson", schema = "bad")
public class ShopLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_lesson_id")
    long id;

    @OneToOne(mappedBy = "shopLesson", cascade = CascadeType.ALL)
    LessonCancel lessonCancel;

    @Column(name = "lesson_id")
    UUID lessonId;

    @Column(name = "inst_nm")
    String instrument;

    @Column(name = "tchr_nm")
    String teacherName;

    @Column(name = "cust_nm")
    String customerName;

    @Column(name = "lesson_dt")
    LocalDateTime lessonDate;

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
