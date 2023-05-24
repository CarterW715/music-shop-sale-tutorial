package com.practice.kafka.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
public class EventLesson extends ChargeableEvent {

    UUID lessonId;
    String instrument;
    String teacherName;
    String customerName;
    LocalDateTime lessonDate;

}
