package com.practice.musicsalesgood.kafka.producer;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

@ApplicationScoped
public class ShopSaleProducer extends MusicShopProducer {

    @Override
    public void publishEvent(MusicShopEvent event) {
        event.getHeader().setEventTimestamp(LocalDateTime.now());
        event.getHeader().setEventType(MusicShopEvents.sold.name());

        musicShopEventEmitter.send(event);
    }
}
