package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.producer.ShopLessonProducer;
import com.practice.musicsalesgood.mapper.EventMapper;
import com.practice.musicsalesgood.repository.ShopLessonRepository;
import com.practice.musicsalesgood.validation.processor.ShopLessonProcessorValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ShopLessonProcessor extends MusicShopEventProcessorWithPublish<ShopLessonProducer, ShopLessonProcessorValidator> {

    @Inject
    ShopLessonRepository shopLessonRepositoryImpl;

    @Override
    public boolean acceptsEventType(String eventType) {
        return eventType.equals(MusicShopEvents.lesson.name());
    }

    @Override
    public String getListenerName() {
        return "ShopLesson";
    }

    @Override
    ShopLessonProcessorValidator getValidator() {
        return new ShopLessonProcessorValidator(shopLessonRepositoryImpl);
    }

    public void processEvent(MusicShopEvent event) {

        var lessonSale = EventMapper.eventToShopLesson(event);

        try {
            shopLessonRepositoryImpl.saveShopLesson(lessonSale);
            log.info("Successfully scheduled lesson: {}", lessonSale.getLessonId());
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

    }

}
