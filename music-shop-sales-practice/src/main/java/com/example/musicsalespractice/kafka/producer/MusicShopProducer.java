package com.example.musicsalespractice.kafka.producer;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.time.LocalDateTime;

public abstract class MusicShopProducer {
    @Inject
    @Channel("event-out")
    Emitter<MusicShopEvent> musicShopEventEmitter;

    public void publishErrorEvent(MusicShopEvent event, String error) {
        event.getHeader().setEventTimestamp(LocalDateTime.now());
        event.getHeader().setEventType(MusicShopEvents.error.name());

        musicShopEventEmitter.send(event);
    }

    public abstract void publishEvent(MusicShopEvent event);
}
