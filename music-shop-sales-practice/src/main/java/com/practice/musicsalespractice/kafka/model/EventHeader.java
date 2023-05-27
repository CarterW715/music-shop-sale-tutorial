package com.practice.musicsalespractice.kafka.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventHeader {

    UUID eventId;
    String eventType;
    LocalDateTime eventTimestamp;
    String version;

}
