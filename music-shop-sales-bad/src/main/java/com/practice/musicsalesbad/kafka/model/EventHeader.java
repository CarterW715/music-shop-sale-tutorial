package com.practice.musicsalesbad.kafka.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventHeader {

    UUID messageId;
    String eventType;
    LocalDateTime eventTimestamp;
    String version;

}
