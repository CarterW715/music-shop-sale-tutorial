package com.practice.kafka.model;

import lombok.Data;

@Data
public class ChargeableEvent {

    Double saleAmt;
    Double tax;
    String promoCode;
    Double discountAmt;
    Double grandTotal;

}
