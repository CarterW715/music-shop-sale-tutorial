package com.practice.kafka.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
public class EventCancel extends RefundableEvent {

    UUID lessonId;

}
