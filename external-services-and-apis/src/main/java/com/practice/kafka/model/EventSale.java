package com.practice.kafka.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
public class EventSale extends ChargeableEvent {

    UUID saleId;
    String instrument;
    String instrumentCode;
    String manufactureNumber;
    String employeeName;
    String customerName;
    LocalDateTime saleDate;

}
