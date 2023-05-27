package com.practice.musicsalespractice.kafka.consumer;

import com.practice.musicsalespractice.kafka.events.MusicShopEvents;
import com.practice.musicsalespractice.kafka.model.MusicShopEvent;
import com.practice.musicsalespractice.kafka.processor.ShopLessonProcessor;
import com.practice.musicsalespractice.kafka.processor.ShopSaleProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@AllArgsConstructor
@ApplicationScoped
public class KafkaEventRouter {

    @Inject
    ShopSaleProcessor shopSaleProcessor;

    @Inject
    ShopLessonProcessor shopLessonProcessor;

    @Blocking
    @Incoming("event-in")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void consume(MusicShopEvent event) {
        log.info("Received kafka event with id: {} and type: {}", event.getHeader().getEventId(), event.getHeader().getEventType());
        if (event.getHeader().getEventType().equals(MusicShopEvents.sale.name())) {
            shopSaleProcessor.processEvent(event);
        } else if (event.getHeader().getEventType().equals(MusicShopEvents.lesson.name())) {
            shopLessonProcessor.processEvent(event);
        }
    }
}
