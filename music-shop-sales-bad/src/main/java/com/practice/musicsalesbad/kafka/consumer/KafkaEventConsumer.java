package com.practice.musicsalesbad.kafka.consumer;

import com.practice.musicsalesbad.kafka.events.MusicShopEvents;
import com.practice.musicsalesbad.kafka.model.MusicShopEvent;
import com.practice.musicsalesbad.service.KafkaEventService;
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
public class KafkaEventConsumer {

    @Inject
    KafkaEventService kafkaEventServiceImpl;

    @Blocking
    @Incoming("event-in")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void consume(MusicShopEvent event) {
        log.info("Received kafka event with id: {} and type: {}", event.getHeader().getMessageId(), event.getHeader().getEventType());

        if (event.getHeader().getEventType().equals(MusicShopEvents.sale.name())) {
            kafkaEventServiceImpl.handleSaleEvent(event);
        } else if (event.getHeader().getEventType().equals(MusicShopEvents.lesson.name())) {
            kafkaEventServiceImpl.handleLessonEvent(event);
        } else if (event.getHeader().getEventType().equals(MusicShopEvents.returns.name())) {
            kafkaEventServiceImpl.handleReturnEvent(event);
        } else if (event.getHeader().getEventType().equals(MusicShopEvents.cancel.name())) {
            kafkaEventServiceImpl.handleCancelEvent(event);
        } else if (event.getHeader().getEventType().equals(MusicShopEvents.sold.name())) {
            kafkaEventServiceImpl.handleWarrantyEvent(event);
            kafkaEventServiceImpl.handleRewardsEvent(event);
        } else if (event.getHeader().getEventType().equals(MusicShopEvents.scheduled.name())) {
            kafkaEventServiceImpl.handleRewardsEvent(event);
        }

    }

}
