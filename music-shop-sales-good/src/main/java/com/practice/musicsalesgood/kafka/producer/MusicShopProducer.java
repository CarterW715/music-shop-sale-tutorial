package com.practice.musicsalesgood.kafka.producer;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public abstract class MusicShopProducer {
    @Inject
    @Channel("test-out")
    Emitter<MusicShopEvent> musicShopEventEmitter;

    public abstract void publishMessage(MusicShopEvent message);
}
