package com.practice.musicsalesbad.kafka.consumer;

import com.practice.musicsalesbad.kafka.events.MusicShopEvents;
import com.practice.musicsalesbad.kafka.model.MusicShopEvent;
import com.practice.musicsalesbad.service.KafkaMessageService;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@AllArgsConstructor
public class KafkaMessageConsumer {

    @Inject
    KafkaMessageService kafkaMessageServiceImpl;

    @Incoming("music_sales_stream_local")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void consume(MusicShopEvent message) {
        log.info("Received kafka message: " + message.toString());

        if (message.getHeader().getEventType().equals(MusicShopEvents.sale.name())) {
            kafkaMessageServiceImpl.handleSaleMessage(message);
        } else if (message.getHeader().getEventType().equals(MusicShopEvents.lesson.name())) {
            kafkaMessageServiceImpl.handleLessonMessage(message);
        }


    }

}
