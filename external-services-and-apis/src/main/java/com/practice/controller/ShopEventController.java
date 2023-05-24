package com.practice.controller;

import com.practice.kafka.ShopEventProducer;
import com.practice.kafka.model.MusicShopEvent;
import com.practice.model.ExternalServiceResponse;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.common.constraint.NotNull;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    public ExternalServiceResponse sendShopReturnEvent(@NotNull ReturnRequest request) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopReturnEvent(request.getSaleId(), request.getRefundAmt()));
    }

    @POST
    @Path("/send/cancel")
    public ExternalServiceResponse sendShopCancelEvent(@NotNull CancelRequest request) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopCancelEvent(request.getLessonId(), request.getRefundAmt()));
    }

    @POST
    @Path("/send/sold")
    public ExternalServiceResponse sendShopSoldEvent(@NotNull UUID saleId) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopSoldEvent(saleId));
    }

    @POST
    @Path("/send/scheduled")
    public ExternalServiceResponse sendShopScheduledEvent(@NotNull UUID lessonId) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopSoldEvent(lessonId));
    }

    @Data
    @AllArgsConstructor
    static class ReturnRequest {
        UUID saleId;
        double refundAmt;
    }

    @Data
    @AllArgsConstructor
    static class CancelRequest {
        UUID lessonId;
        double refundAmt;
    }
}
