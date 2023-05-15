package com.practice.musicsalesgood.kafka.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventSale extends ChargeableEvent {

    UUID saleId;
    String instrument;
    String instrumentCode;
    String manufactureNumber;
    String employeeName;
    String customerName;
    LocalDateTime saleDate;

}
