package com.practice.musicsalesbad.controller;

import com.practice.musicsalesbad.kafka.model.MusicShopEvent;
import com.practice.musicsalesbad.model.ShopControllerResponseStr;
import com.practice.musicsalesbad.model.ShopControllerResponseStrList;
import com.practice.musicsalesbad.service.KafkaEventService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Produces("application/json")
@Path("/v1/kafka/event")
@RegisterForReflection
public class ProcessorController {

    @Inject
    KafkaEventService kafkaEventServiceImpl;

    @GET
    @Path("/sale/run")
    public ShopControllerResponseStrList getHandlerNames() {
        var handlerNames = List.of(
                "Sale",
                "Lesson",
                "Sale Return",
                "Lesson Cancel",
                "Warranty",
                "Rewards"
        );
        
        return ShopControllerResponseStrList.success(handlerNames);
    }
    
    @POST
    @Path("/sale/run")
    public ShopControllerResponseStr runSaleEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleSaleEvent(event);
        } catch (Exception ex) {
            return ShopControllerResponseStr.failed("Error processing message. Check logs for details");
        }
        return ShopControllerResponseStr.success("successfully submitted event. check logs details");
    }

    @POST
    @Path("/lesson/run")
    public ShopControllerResponseStr runLessonEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleReturnEvent(event);
        } catch (Exception ex) {
            return ShopControllerResponseStr.failed("Error processing message. Check logs for details");
        }
        return ShopControllerResponseStr.success("successfully submitted event. check logs details");
    }

    @POST
    @Path("/return/run")
    public ShopControllerResponseStr runReturnEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleReturnEvent(event);
        } catch (Exception ex) {
            return ShopControllerResponseStr.failed("Error processing message. Check logs for details");
        }
        return ShopControllerResponseStr.success("successfully submitted event. check logs details");
    }

    @POST
    @Path("/cancel/run")
    public ShopControllerResponseStr runCancelEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleCancelEvent(event);
        } catch (Exception ex) {
            return ShopControllerResponseStr.failed("Error processing message. Check logs for details");
        }
        return ShopControllerResponseStr.success("successfully submitted event. check logs details");
    }

    @POST
    @Path("/sold/run")
    public ShopControllerResponseStr runSoldEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleWarrantyEvent(event);
            kafkaEventServiceImpl.handleRewardsEvent(event);
        } catch (Exception ex) {
            return ShopControllerResponseStr.failed("Error processing message. Check logs for details");
        }
        return ShopControllerResponseStr.success("successfully submitted event. check logs details");
    }

    @POST
    @Path("/scheduled/run")
    public ShopControllerResponseStr runScheduledEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleRewardsEvent(event);
        } catch (Exception ex) {
            return ShopControllerResponseStr.failed("Error processing message. Check logs for details");
        }
        return ShopControllerResponseStr.success("successfully submitted event. check logs details");
    }
}
