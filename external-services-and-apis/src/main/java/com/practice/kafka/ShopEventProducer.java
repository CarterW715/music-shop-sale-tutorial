package com.practice.kafka;

import com.practice.enums.ProjectType;
import com.practice.kafka.model.MusicShopEvent;
import com.practice.service.MusicEventService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.UUID;

@ApplicationScoped
public class ShopEventProducer {

    @Inject
    @Channel("message-out-good")
    Emitter<MusicShopEvent> messageEmitterGood;

    @Inject
    @Channel("message-out-bad")
    Emitter<MusicShopEvent> messageEmitterBad;

    @Inject
    @Channel("message-out-practice")
    Emitter<MusicShopEvent> messageEmitterPractice;

    @Inject
    MusicEventService musicEventServiceImpl;

    private Emitter<MusicShopEvent> getMessageEmitter(ProjectType projectType) {
        switch (projectType) {
            default:
            case GOOD:
                return messageEmitterGood;
            case BAD:
                return messageEmitterBad;
            case PRACTICE:
                return messageEmitterPractice;
        }
    }

    public MusicShopEvent publishMusicShopSaleEvent(ProjectType projectType) {
        var event = musicEventServiceImpl.createSaleEvent();
        getMessageEmitter(projectType).send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopLessonEvent(ProjectType projectType) {
        var event = musicEventServiceImpl.createLessonEvent();
        getMessageEmitter(projectType).send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopReturnEvent(ProjectType projectType, UUID saleId, double refundAmt) {
        var event = musicEventServiceImpl.createReturnEvent(saleId, refundAmt);
        getMessageEmitter(projectType).send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopCancelEvent(ProjectType projectType, UUID lessonId, double refundAmt) {
        var event = musicEventServiceImpl.createCancelEvent(lessonId, refundAmt);
        getMessageEmitter(projectType).send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopSoldEvent(ProjectType projectType, UUID saleId) {
        var event = musicEventServiceImpl.createSoldEvent(saleId);
        getMessageEmitter(projectType).send(event);
        return event;
    }

    public MusicShopEvent publishMusicShopScheduledEvent(ProjectType projectType, UUID lessonId) {
        var event = musicEventServiceImpl.createScheduledEvent(lessonId);
        getMessageEmitter(projectType).send(event);
        return event;
    }
}
