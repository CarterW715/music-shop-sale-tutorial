package com.practice.musicsalesgood.kafka.producer;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;

public abstract class MusicSaleProducer {
    public abstract void publishMessage(MusicShopEvent message);
}
