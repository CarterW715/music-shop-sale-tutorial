package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.audit.model.MusicShopEventAuditRecord;
import com.practice.musicsalesgood.exception.ProcessorValidationException;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.producer.MusicSaleProducer;
import com.practice.musicsalesgood.validation.processor.ProcessorValidator;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MusicShopEventProcessorWithPublish<T extends MusicSaleProducer, T2 extends ProcessorValidator> extends MusicShopEventProcessor<T2> {

    @Inject
    T eventProducer;

    public void handleMessage(MusicShopEvent event) {

        try {
            getValidator().validateEvent(event);
            // Run business logic for implementing listener
            processEvent(event);
        } catch (ProcessorValidationException ex) {
            log.error("Event failed processor validation with message: {}", ex.getMessage());
        } catch (Exception ex) {
            log.error(String.format("Unhandled Exception :: (%s) for Message %s", ex.getMessage(), "placeholder"));
        }

        eventProducer.publishMessage(event);

//        s3AuditListenerServiceImpl.saveUvEventAuditRecord(auditRecord, getAuditFileName());
    }

}
