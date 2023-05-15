package com.practice.musicsalesgood.kafka.processor;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.producer.SaleReturnProducer;
import com.practice.musicsalesgood.mapper.MessageMapper;
import com.practice.musicsalesgood.repository.SaleReturnRepository;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import com.practice.musicsalesgood.validation.processor.SaleReturnProcessorValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class SaleReturnProcessor extends MusicShopEventProcessorWithPublish<SaleReturnProducer, SaleReturnProcessorValidator> {

    @Inject
    ShopSaleRepository shopSaleRepositoryImpl;

    @Inject
    SaleReturnRepository shopReturnCancelRepositoryImpl;

    @Override
    public boolean acceptsEventType(String eventType) {
        return eventType.equals(MusicShopEvents.returns.name());
    }

    @Override
    public String getListenerName() {
        return "SaleReturn";
    }

    @Override
    SaleReturnProcessorValidator getValidator() {
        return new SaleReturnProcessorValidator(shopReturnCancelRepositoryImpl, shopSaleRepositoryImpl);
    }

    public void processEvent(MusicShopEvent message) {

        var sale = shopSaleRepositoryImpl.getSaleBySaleId(message.getReturns().getSaleId()).get();

        var shopReturn = MessageMapper.MessageToSaleReturn(message, sale);

        try {
            shopReturnCancelRepositoryImpl.saveShopReturn(shopReturn);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

    }

}
