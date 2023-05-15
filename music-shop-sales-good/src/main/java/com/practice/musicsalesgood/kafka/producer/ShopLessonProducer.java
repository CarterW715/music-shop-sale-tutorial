package com.practice.musicsalesgood.kafka.producer;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;

import java.time.LocalDateTime;

public class ShopLessonProducer extends MusicShopProducer {

    @Override
    public void publishMessage(MusicShopEvent message) {
        message.getHeader().setEventTimestamp(LocalDateTime.now());
        message.getHeader().setEventType(MusicShopEvents.scheduled.name());

        musicShopEventEmitter.send(message);
    }
}
