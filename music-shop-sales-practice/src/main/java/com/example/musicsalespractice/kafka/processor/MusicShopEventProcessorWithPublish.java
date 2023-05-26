package com.example.musicsalespractice.kafka.processor;

import com.practice.musicsalesgood.exception.ProcessorValidationException;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.producer.MusicShopProducer;
import com.practice.musicsalesgood.validation.processor.ProcessorValidator;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MusicShopEventProcessorWithPublish<T extends MusicShopProducer, T2 extends ProcessorValidator> extends MusicShopEventProcessor<T2> {

    @Inject
    T eventProducer;

    public void handleEvent(MusicShopEvent event) {

        try {
            getValidator().validateEvent(event);
            // Run business logic for implementing listener
            processEvent(event);
            eventProducer.publishEvent(event);
        } catch (ProcessorValidationException ex) {
            log.error("Event failed processor validation with message: {}", ex.getMessage());
            eventProducer.publishErrorEvent(event, ex.getMessage());
        } catch (Exception ex) {
            log.error(String.format("Error processing event: %s with error: %s", event.getHeader().getEventId(), ex.getMessage()));
            eventProducer.publishErrorEvent(event, ex.getMessage());
        }

    }

}
