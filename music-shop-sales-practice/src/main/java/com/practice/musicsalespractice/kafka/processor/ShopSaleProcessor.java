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
public class ShopSaleProcessor {
    @Inject
    ShopTransactionRepository shopTransactionRepositoryImpl;

    @Inject
    KafkaEventProducer kafkaEventProducer;

    public void processEvent(MusicShopEvent event) {
        var sale = EventMapper.eventToShopSale(event);

        try {
            shopTransactionRepositoryImpl.saveShopSale(sale);
            log.info("Successfully sold instrument: {}", sale.getSaleId());
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

        kafkaEventProducer.publishSoldMessage(event);
    }
}
