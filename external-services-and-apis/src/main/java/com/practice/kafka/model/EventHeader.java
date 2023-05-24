package com.practice.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventHeader {

    UUID messageId;
    String eventType;
    LocalDateTime eventTimestamp;
    String version;

}
