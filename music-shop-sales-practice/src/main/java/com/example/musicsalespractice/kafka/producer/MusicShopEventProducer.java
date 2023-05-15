package com.example.musicsalespractice.kafka.producer;

import com.example.musicsalespractice.kafka.events.MusicShopEvents;
import com.example.musicsalespractice.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;

import java.time.LocalDateTime;

@ApplicationScoped
public class MusicShopEventProducer {

    public void publishSoldEvent(MusicShopEvent message) {
        message.getHeader().setEventTimestamp(LocalDateTime.now());
        message.getHeader().setEventType(MusicShopEvents.sold.name());

        musicShopEventEmitter.send(message);
    }

    public void publishScheduledEvent(MusicShopEvent message) {
        message.getHeader().setEventTimestamp(LocalDateTime.now());
        message.getHeader().setEventType(MusicShopEvents.scheduled.name());

        musicShopEventEmitter.send(message);
    }
}
