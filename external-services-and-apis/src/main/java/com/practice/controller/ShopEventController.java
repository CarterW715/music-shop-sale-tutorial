package com.practice.controller;

import com.practice.kafka.ShopEventProducer;
import com.practice.kafka.model.MusicShopEvent;
import com.practice.model.ExternalServiceResponse;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.common.constraint.NotNull;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/kafka/processor")
@RegisterForReflection
public class ShopEventController {

    @Inject
    ShopEventProducer shopEventProducer;

    @POST
    @Path("/send/sale")
    public ExternalServiceResponse sendShopSaleEvent() {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopSaleEvent());
    }

    @POST
    @Path("/send/lesson")
    public ExternalServiceResponse sendShopLessonEvent() {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopLessonEvent());
    }

    @POST
    @Path("/send/return")
    public ExternalServiceResponse sendShopReturnEvent(@NotNull UUID saleId, @NotNull double refundAmt) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopReturnEvent(saleId, refundAmt));
    }

    @POST
    @Path("/send/cancel")
    public ExternalServiceResponse sendShopCancelEvent(@NotNull UUID saleId, @NotNull double refundAmt) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopCancelEvent(saleId, refundAmt));
    }

    @POST
    @Path("/send/sold")
    public ExternalServiceResponse sendShopSoldEvent() {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopSaleEvent());
    }

    @POST
    @Path("/send/scheduled")
    public ExternalServiceResponse sendJson() {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopScheduledEvent());
    }

}
