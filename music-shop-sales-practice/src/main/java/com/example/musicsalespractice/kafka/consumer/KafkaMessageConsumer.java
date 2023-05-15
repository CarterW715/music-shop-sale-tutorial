package com.example.musicsalespractice.kafka.consumer;

import com.example.musicsalespractice.kafka.events.MusicShopEvents;
import com.example.musicsalespractice.kafka.model.MusicShopEvent;
import com.example.musicsalespractice.kafka.processor.ShopTransactionProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.musicsalesgood.factory.KafkaProcessorFactory;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@AllArgsConstructor
public class KafkaMessageConsumer {

    @Inject
    ShopTransactionProcessor shopTransactionProcessor;

    @Incoming("music_sales_stream_local")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void consume(MusicShopEvent message) {
        log.info("Received kafka message: " + message.toString());
        if (event.getHeader().getEventType().equals(MusicShopEvents.sale.name()) ||
                event.getHeader().getEventType().equals(MusicShopEvents.lesson.name())) {
            shopTransactionProcessor.processEvent(message);
        }
    }

}
