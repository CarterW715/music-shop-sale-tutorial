package com.practice.musicsalesbad.service;

import com.practice.musicsalesbad.kafka.model.MusicShopEvent;

public interface KafkaEventService {

    void handleSaleEvent(MusicShopEvent event);

    void handleLessonEvent(MusicShopEvent event);

    void handleReturnEvent(MusicShopEvent event);

    void handleCancelEvent(MusicShopEvent event);

    void handleWarrantyEvent(MusicShopEvent event);

    void handleRewardsEvent(MusicShopEvent event);
}
