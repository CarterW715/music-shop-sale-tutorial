package com.practice.musicsalesgood.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.musicsalesgood.factory.KafkaProcessorFactory;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
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
public class KafkaMessageRouter {

    @Inject
    KafkaProcessorFactory kafkaProcessorFactoryImpl;

    ObjectMapper objectMapper;

    @Blocking
    @Incoming("message-in")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void consume(MusicShopEvent message) {
        log.debug("Received kafka message: " + message.toString());
        kafkaProcessorFactoryImpl.getKafkaProcessors().forEach(listener -> {
            if (listener.acceptsEventType(message.getHeader().getEventType()))
                listener.handleMessage(message);
        });
    }
}
