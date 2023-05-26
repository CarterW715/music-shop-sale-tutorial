package com.practice.musicsalesbad.controller;

import com.practice.musicsalesbad.kafka.model.MusicShopEvent;
import com.practice.musicsalesbad.service.KafkaEventService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

@Slf4j
@Produces("application/json")
@Path("/v1/kafka/event")
@RegisterForReflection
public class ProcessorController {

    @Inject
    KafkaEventService kafkaEventServiceImpl;

    @POST
    @Path("/sale/run")
    public RestResponse<String> runSaleEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleSaleEvent(event);
        } catch (Exception ex) {
            return RestResponse.status(400, "Error processing message. Check logs for details");
        }
        return RestResponse.status(200, "successfully submitted event. check logs details");
    }

    @POST
    @Path("/lesson/run")
    public RestResponse<String> runLessonEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleReturnEvent(event);
        } catch (Exception ex) {
            return RestResponse.status(400, "Error processing message. Check logs for details");
        }
        return RestResponse.status(200, "successfully submitted event. check logs details");
    }

    @POST
    @Path("/return/run")
    public RestResponse<String> runReturnEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleReturnEvent(event);
        } catch (Exception ex) {
            return RestResponse.status(400, "Error processing message. Check logs for details");
        }
        return RestResponse.status(200, "successfully submitted event. check logs details");
    }

    @POST
    @Path("/cancel/run")
    public RestResponse<String> runCancelEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleCancelEvent(event);
        } catch (Exception ex) {
            return RestResponse.status(400, "Error processing message. Check logs for details");
        }
        return RestResponse.status(200, "successfully submitted event. check logs details");
    }

    @POST
    @Path("/sold/run")
    public RestResponse<String> runSoldEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleWarrantyEvent(event);
            kafkaEventServiceImpl.handleRewardsEvent(event);
        } catch (Exception ex) {
            return RestResponse.status(400, "Error processing message. Check logs for details");
        }
        return RestResponse.status(200, "successfully submitted event. check logs details");
    }

    @POST
    @Path("/scheduled/run")
    public RestResponse<String> runScheduledEvent(@NonNull MusicShopEvent event) {
        try {
            kafkaEventServiceImpl.handleRewardsEvent(event);
        } catch (Exception ex) {
            return RestResponse.status(400, "Error processing message. Check logs for details");
        }
        return RestResponse.status(200, "successfully submitted event. check logs details");
    }
}
