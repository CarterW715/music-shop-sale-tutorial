package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.exception.ProcessorValidationException;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.validation.processor.ProcessorValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MusicShopEventProcessor<T extends ProcessorValidator> implements KafkaProcessor {

    abstract void processEvent(MusicShopEvent event);

    abstract T getValidator();

    public void handleEvent(MusicShopEvent event) {
        try {
            getValidator().validateEvent(event);
            // Run business logic for implementing listener
            processEvent(event);
        } catch (ProcessorValidationException ex) {
            log.error("Event failed processor validation with message: {}", ex.getMessage());
        } catch (Exception ex) {
            log.error(String.format("Unhandled Exception :: (%s) for Message %s", ex.getMessage(), "placeholder"));
        }

//        s3AuditListenerServiceImpl.saveUvEventAuditRecord(auditRecord, getAuditFileName());
    }

}
