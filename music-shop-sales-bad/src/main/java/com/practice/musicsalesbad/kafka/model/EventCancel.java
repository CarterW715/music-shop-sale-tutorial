package com.practice.musicsalesbad.kafka.model;

import lombok.Data;

import java.util.UUID;

@Data
public class EventCancel extends RefundableEvent {

    UUID lessonId;

}
