package com.practice.musicsalesbad.kafka.processor;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.mapper.MessageMapper;
import com.practice.musicsalesgood.mapper.WarrantyMapper;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import com.practice.musicsalesgood.repository.WarrantyRepository;
import com.practice.musicsalesgood.service.rest.WarrantyServiceRest;
import com.practice.musicsalesgood.validation.processor.WarrantyProcessorValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class WarrantyProcessor extends MusicShopEventProcessor<WarrantyProcessorValidator> {

    @Inject
    ShopSaleRepository shopTransactionRepositoryImpl;

    @Inject
    WarrantyRepository warrantyRepositoryImpl;

    @Inject
    WarrantyServiceRest warrantyServiceRest;

    @Override
    public boolean acceptsEventType(String eventType) {
        return eventType.equals(MusicShopEvents.sold.name());
    }

    @Override
    public String getListenerName() {
        return "Warranty";
    }

    @Override
    WarrantyProcessorValidator getValidator() {
        return new WarrantyProcessorValidator(shopTransactionRepositoryImpl);
    }

    public void processEvent(MusicShopEvent message) {

        var request = MessageMapper.MessageToWarrantyRequest(message);

        try {
            var response = warrantyServiceRest.submitWarranty(request);
            var warranty = WarrantyMapper.WarrantyResponseToEntity(response.getEntity(), message);
            warrantyRepositoryImpl.saveWarranty(warranty);
        } catch (WebApplicationException ex) {
            log.error("Could not submit warranty successfully", ex);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

    }

}
