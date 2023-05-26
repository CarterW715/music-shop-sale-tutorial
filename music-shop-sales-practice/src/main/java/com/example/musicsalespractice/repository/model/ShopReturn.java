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
@Table(name = "shop_return", schema = "good")
public class ShopReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_return_id")
    long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "shop_sale_id", referencedColumnName = "shop_sale_id")
    ShopSale shopSale;

    @Column(name = "rfnd_amt")
    Double refundAmount;

    @Column(name = "rtrn_dt")
    LocalDateTime returnDate;

    @Column(name = "msg_id")
    UUID messageId;

    @Column(name = "evnt_ts")
    LocalDateTime eventTimestamp;

    @Column(name = "msg_version")
    String version;
}
