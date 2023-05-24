package com.practice.kafka.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RefundableEvent {

    Double refundAmt;

}
