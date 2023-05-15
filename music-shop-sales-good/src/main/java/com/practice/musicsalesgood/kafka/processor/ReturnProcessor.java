package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.audit.model.DbProcessorAuditRecord;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.producer.PlaceholderProducer;
import com.practice.musicsalesgood.mapper.MessageMapper;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ReturnProcessor extends MusicShopEventProcessorWithPublish<PlaceholderProducer, DbProcessorAuditRecord> {

    @Inject
    ShopSaleRepository shopSaleRepositoryImpl;

    @Override
    public boolean acceptsEventType(String eventType) {
        return eventType.equals("return");
    }

    @Override
    public String getListenerName() {
        return "Return";
    }

    @Override
    DbProcessorAuditRecord createAuditRecord(MusicShopEvent event) {
        return new DbProcessorAuditRecord();
    }

    @Override
    String getAuditFileName() {
        return null;
    }

    public void processEvent(MusicShopEvent message, DbProcessorAuditRecord record) {

        var musicSale = MessageMapper.MessageToShopSale(message);

        try {
            shopSaleRepositoryImpl.saveShopSale(musicSale);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

    }

}
