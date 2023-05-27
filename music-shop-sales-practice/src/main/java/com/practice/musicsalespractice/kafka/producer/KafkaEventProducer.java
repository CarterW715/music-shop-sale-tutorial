package com.practice.musicsalespractice.kafka.producer;

import com.practice.musicsalespractice.kafka.events.MusicShopEvents;
import com.practice.musicsalespractice.kafka.model.MusicShopEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.time.LocalDateTime;

@ApplicationScoped
public class KafkaEventProducer {
    @Inject
    @Channel("message-out")
    Emitter<MusicShopEvent> musicShopEventEmitter;

    public void publishSoldMessage(MusicShopEvent message) {
        message.getHeader().setEventTimestamp(LocalDateTime.now());
        message.getHeader().setEventType(MusicShopEvents.sold.name());

        musicShopEventEmitter.send(message);
    }

    public void publishScheduledMessage(MusicShopEvent message) {
        message.getHeader().setEventTimestamp(LocalDateTime.now());
        message.getHeader().setEventType(MusicShopEvents.scheduled.name());

        musicShopEventEmitter.send(message);
    }
}
