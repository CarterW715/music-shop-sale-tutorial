package com.practice.musicsalespractice.kafka.processor;

import com.practice.musicsalespractice.kafka.model.MusicShopEvent;
import com.practice.musicsalespractice.kafka.producer.KafkaEventProducer;
import com.practice.musicsalespractice.mapper.EventMapper;
import com.practice.musicsalespractice.repository.ShopTransactionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ShopLessonProcessor {
    @Inject
    ShopTransactionRepository shopTransactionRepositoryImpl;

    @Inject
    KafkaEventProducer kafkaEventProducer;

    public void processEvent(MusicShopEvent event) {

        var lessonSale = EventMapper.eventToShopLesson(event);

        try {
            shopTransactionRepositoryImpl.saveShopLesson(lessonSale);
            log.info("Successfully scheduled lesson: {}", lessonSale.getLessonId());
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

        kafkaEventProducer.publishSoldMessage(event);
    }

}
