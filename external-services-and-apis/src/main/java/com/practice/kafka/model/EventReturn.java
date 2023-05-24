package com.practice.kafka.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
public class EventReturn extends RefundableEvent {

    UUID saleId;
    LocalDateTime returnDate;

}
