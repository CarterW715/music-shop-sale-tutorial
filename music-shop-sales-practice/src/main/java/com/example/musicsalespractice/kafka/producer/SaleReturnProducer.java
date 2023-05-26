package com.example.musicsalespractice.kafka.producer;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

@ApplicationScoped
public class SaleReturnProducer extends MusicShopProducer {

    @Override
    public void publishEvent(MusicShopEvent event) {
        event.getHeader().setEventTimestamp(LocalDateTime.now());
        event.getHeader().setEventType(MusicShopEvents.returned.name());

        musicShopEventEmitter.send(event);
    }
}
