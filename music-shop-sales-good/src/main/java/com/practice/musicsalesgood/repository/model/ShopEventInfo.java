//package com.practice.musicsalesgood.repository.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@Entity
//@Builder(toBuilder = true)
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "shop_event_info", schema = "good")
//public class ShopEventInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "shop_event_info_id")
//    long id;
//
////    @Builder.Default
////    @OneToOne(mappedBy = "shopEventInfo", cascade = CascadeType.ALL)
////    ShopSale shopSale = ShopSale.builder().build();
//
//    @Column(name = "msg_id")
//    String messageId;
//
//    @Column(name = "evnt_type")
//    String eventType;
//
//    @Column(name = "evnt_ts")
//    LocalDateTime eventTimestamp;
//
//    String version;
//
//    String shopId;
//
//    @Column(name = "shop_ctry")
//    String shopCountry;
//
//    @Column(name = "shop_st")
//    String shopState;
//
//    @Column(name = "shop_nm")
//    String shopName;
//}
