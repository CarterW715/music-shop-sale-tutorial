package com.practice.musicsalesgood.kafka.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventReturn extends RefundableEvent {

    UUID saleId;
    LocalDateTime returnDate;

}
