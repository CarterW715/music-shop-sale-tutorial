package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.producer.LessonCanceledProducer;
import com.practice.musicsalesgood.mapper.MessageMapper;
import com.practice.musicsalesgood.repository.LessonCancelRepository;
import com.practice.musicsalesgood.repository.ShopLessonRepository;
import com.practice.musicsalesgood.validation.processor.LessonCancelProcessorValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class LessonCancelProcessor extends MusicShopEventProcessorWithPublish<LessonCanceledProducer, LessonCancelProcessorValidator> {

    @Inject
    ShopLessonRepository shopTransactionRepositoryImpl;

    @Inject
    LessonCancelRepository shopReturnCancelRepositoryImpl;

    @Override
    public boolean acceptsEventType(String eventType) {
        return eventType.equals(MusicShopEvents.cancel.name());
    }

    @Override
    public String getListenerName() {
        return "SaleCancel";
    }

    @Override
    LessonCancelProcessorValidator getValidator() {
        return new LessonCancelProcessorValidator(shopReturnCancelRepositoryImpl, shopTransactionRepositoryImpl);
    }

    public void processEvent(MusicShopEvent message) {

        var lesson = shopTransactionRepositoryImpl.getLessonByLessonId(message.getCancel().getLessonId()).get();

        var lessonCancel = MessageMapper.messageToLessonCancel(message, lesson);

        try {
            shopReturnCancelRepositoryImpl.saveLessonCancel(lessonCancel);
            log.info("Successfully canceled lesson: {}", lesson.getLessonId());
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

    }

}
