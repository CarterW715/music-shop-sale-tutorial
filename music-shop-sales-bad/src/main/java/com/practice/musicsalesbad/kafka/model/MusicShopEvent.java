package com.practice.musicsalesbad.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicShopEvent {

    EventHeader header;
    EventShop shop;
    EventSale sale;
    EventLesson lesson;
    EventReturn returns;
    EventCancel cancel;

}
