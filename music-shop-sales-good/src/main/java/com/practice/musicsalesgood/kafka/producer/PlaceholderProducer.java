package com.practice.musicsalesgood.kafka.producer;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlaceholderProducer extends MusicShopProducer {

    @Override
    public void publishMessage(MusicShopEvent message) {
        return;
    }
}
