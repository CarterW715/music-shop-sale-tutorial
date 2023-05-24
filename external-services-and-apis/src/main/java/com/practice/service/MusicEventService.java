package com.practice.service;

import com.practice.kafka.model.MusicShopEvent;

import java.util.UUID;

public interface MusicEventService {

    MusicShopEvent createSaleEvent();

    MusicShopEvent createLessonEvent();

    MusicShopEvent createReturnEvent(UUID saleId, double refundAmt);

    MusicShopEvent createCancelEvent(UUID lessonId, double refundAmt);

    MusicShopEvent createSoldEvent(UUID saleId);

    MusicShopEvent createScheduledEvent(UUID lessonId);

}
