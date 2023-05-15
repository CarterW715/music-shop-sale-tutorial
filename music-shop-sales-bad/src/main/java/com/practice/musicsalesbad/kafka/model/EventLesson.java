package com.practice.musicsalesbad.kafka.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventLesson extends ChargeableEvent {

    UUID lessonId;
    String instrument;
    String teacherName;
    String customerName;
    LocalDateTime lessonDate;

}
