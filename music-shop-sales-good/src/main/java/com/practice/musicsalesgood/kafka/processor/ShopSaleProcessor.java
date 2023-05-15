package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.producer.PlaceholderProducer;
import com.practice.musicsalesgood.mapper.MessageMapper;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import com.practice.musicsalesgood.validation.processor.ShopSaleProcessorValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ShopSaleProcessor extends MusicShopEventProcessorWithPublish<PlaceholderProducer, ShopSaleProcessorValidator> {

    @Inject
    ShopSaleRepository shopSaleRepositoryImpl;

    @Override
    public boolean acceptsEventType(String eventType) {
        return eventType.equals(MusicShopEvents.sale.name());
    }

    @Override
    public String getListenerName() {
        return "ShopSale";
    }

    @Override
    ShopSaleProcessorValidator getValidator() {
        return new ShopSaleProcessorValidator(shopSaleRepositoryImpl);
    }

    public void processEvent(MusicShopEvent message) {
        var musicSale = MessageMapper.MessageToShopSale(message);

        try {
            shopSaleRepositoryImpl.saveShopSale(musicSale);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }
    }
}
