package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.audit.model.DbProcessorAuditRecord;
import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.producer.PlaceholderProducer;
import com.practice.musicsalesgood.kafka.producer.ShopLessonProducer;
import com.practice.musicsalesgood.mapper.MessageMapper;
import com.practice.musicsalesgood.repository.ShopLessonRepository;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import com.practice.musicsalesgood.validation.processor.ShopLessonProcessorValidator;
import com.practice.musicsalesgood.validation.processor.ShopSaleProcessorValidator;
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
        return eventType.equals(MusicShopEvents.sale.name());
    }

    @Override
    public String getListenerName() {
        return "ShopLesson";
    }

    @Override
    ShopLessonProcessorValidator getValidator() {
        return new ShopLessonProcessorValidator(shopLessonRepositoryImpl);
    }

    public void processEvent(MusicShopEvent message) {

        var lessonSale = MessageMapper.MessageToShopLesson(message);

        try {
            shopLessonRepositoryImpl.saveShopLesson(lessonSale);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

    }

}
