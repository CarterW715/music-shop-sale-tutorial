package com.practice.kafka;

import com.practice.kafka.model.MusicShopEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class ShopEventProducer {

    @Inject
    @Channel("music_sales_stream_local")
    Emitter<MusicShopEvent> testMessageEmitter;

    public void publishMusicShopMessage() {

        var testMessage = MusicShopEvent.builder().build();

        var ack = testMessageEmitter.send(testMessage);

    }

}
