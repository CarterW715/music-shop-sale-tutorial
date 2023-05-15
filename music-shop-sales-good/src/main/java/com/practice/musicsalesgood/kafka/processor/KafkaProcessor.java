package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;

public interface KafkaProcessor {
    void handleMessage(MusicShopEvent event);
    boolean acceptsEventType(String eventType);
    String getListenerName();
}
