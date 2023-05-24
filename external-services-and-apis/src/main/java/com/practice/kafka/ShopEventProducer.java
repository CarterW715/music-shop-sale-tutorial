package com.practice.kafka;

import com.practice.kafka.model.MusicShopEvent;
import com.practice.service.MusicEventService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.UUID;

@ApplicationScoped
public class ShopEventProducer {

    @Inject
    @Channel("message-out-good")
    Emitter<MusicShopEvent> messageEmitterGood;

    @Inject
    MusicEventService musicEventServiceImpl;

    public MusicShopEvent publishMusicShopSaleEvent() {
        var event = musicEventServiceImpl.createSaleEvent();
        messageEmitterGood.send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopLessonEvent() {
        var event = musicEventServiceImpl.createLessonEvent();
        messageEmitterGood.send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopReturnEvent(UUID saleId, double refundAmt) {
        var event = musicEventServiceImpl.createReturnEvent(saleId, refundAmt);
        messageEmitterGood.send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopCancelEvent(UUID saleId, double refundAmt) {
        var event = musicEventServiceImpl.createCancelEvent(saleId, refundAmt);
        messageEmitterGood.send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopSoldEvent() {
        var event = musicEventServiceImpl.createSoldEvent();
        messageEmitterGood.send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopScheduledEvent() {
        var event = musicEventServiceImpl.createScheduledEvent();
        messageEmitterGood.send(event);
        return event;
    }

}
