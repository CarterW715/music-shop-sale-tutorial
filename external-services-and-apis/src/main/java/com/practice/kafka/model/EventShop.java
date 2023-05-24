package com.practice.kafka.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EventShop {

    UUID shopId;
    String country;
    String state;
    String name;

}
