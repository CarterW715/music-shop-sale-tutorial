package com.practice.kafka.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ChargeableEvent {

    Double saleAmt;
    Double tax;
    String promoCode;
    Double discountAmt;
    Double grandTotal;

}
