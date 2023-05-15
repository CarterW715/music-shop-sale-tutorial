package com.practice.musicsalesbad.service;

import com.practice.musicsalesbad.kafka.model.MusicShopEvent;

public interface KafkaMessageService {

    void handleSaleMessage(MusicShopEvent event);

    void handleLessonMessage(MusicShopEvent event);
}
