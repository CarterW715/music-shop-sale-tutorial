package com.example.musicsalespractice.kafka.processor;

import com.practice.musicsalesgood.kafka.events.MusicShopEvents;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.mapper.EventMapper;
import com.practice.musicsalesgood.mapper.WarrantyMapper;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import com.practice.musicsalesgood.repository.WarrantyRepository;
import com.practice.musicsalesgood.service.rest.WarrantyServiceRest;
import com.practice.musicsalesgood.validation.processor.WarrantyProcessorValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
@ApplicationScoped
public class WarrantyProcessor extends MusicShopEventProcessor<WarrantyProcessorValidator> {

    @Inject
    ShopSaleRepository shopSaleRepositoryImpl;

    @Inject
    WarrantyRepository warrantyRepositoryImpl;

    @RestClient
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
        return new WarrantyProcessorValidator(shopSaleRepositoryImpl);
    }

    public void processEvent(MusicShopEvent event) {

        var request = EventMapper.eventToWarrantyRequest(event);

        var sale = shopSaleRepositoryImpl.getSaleBySaleId(event.getSale().getSaleId()).get();

        try {
            var response = warrantyServiceRest.submitWarranty(request);
            var warranty = WarrantyMapper.warrantyResponseToEntity(response.getData(), event, sale);
            warrantyRepositoryImpl.saveWarranty(warranty);
            log.info("Successfully started warranty for sale: {}", sale.getSaleId());
        } catch (WebApplicationException ex) {
            log.error("Could not submit warranty successfully", ex);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

    }

}
