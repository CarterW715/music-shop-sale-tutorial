package com.example.musicsalespractice.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "sale_warranty", schema = "good")
public class SaleWarranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_warranty_id")
    long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "shop_sale_id", referencedColumnName = "shop_sale_id")
    ShopSale shopSale;

    @Column(name = "wrnty_strt_dt")
    LocalDateTime warrantyStart;

    @Column(name = "wrnty_end_dt")
    LocalDateTime warrantyEnd;

    @Column(name = "wrnty_nm")
    String warrantName;

    @Column(name = "wrnty_cd")
    String warrantyCode;

    @Column(name = "msg_id")
    UUID messageId;

    @Column(name = "evnt_ts")
    LocalDateTime eventTimestamp;

    @Column(name = "msg_version")
    String version;
}
