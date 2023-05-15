package com.practice.musicsalesbad.kafka.producer;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;

import java.time.LocalDateTime;

public class SaleReturnProducer extends MusicShopProducer {

    @Override
    public void publishMessage(MusicShopEvent message) {
        message.getHeader().setEventTimestamp(LocalDateTime.now());
        message.getHeader().setEventType(MusicShopEvents.returned.name());

        musicShopEventEmitter.send(message);
    }
}
