package com.example.musicsalespractice.kafka.processor;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;

public interface KafkaProcessor {
    void handleEvent(MusicShopEvent event);
    boolean acceptsEventType(String eventType);
    String getListenerName();
}
