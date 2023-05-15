package com.practice.musicsalesgood.kafka.model;

import lombok.Data;

import java.util.UUID;

@Data
public class EventShop {

    UUID shopId;
    String country;
    String state;
    String name;

}
