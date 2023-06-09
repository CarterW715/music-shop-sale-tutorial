package com.practice.controller;

import com.practice.enums.ProjectType;
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
    public ExternalServiceResponse<MusicShopEvent> sendShopSaleEvent(@QueryParam("projType") ProjectType projectType) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopSaleEvent(projectType));
    }

    @POST
    @Path("/send/lesson")
    public ExternalServiceResponse<MusicShopEvent> sendShopLessonEvent(@QueryParam("projType") ProjectType projectType) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopLessonEvent(projectType));
    }

    @POST
    @Path("/send/return")
    public ExternalServiceResponse<MusicShopEvent> sendShopReturnEvent(@QueryParam("projType") ProjectType projectType,
                                                       @NotNull ReturnRequest request) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopReturnEvent(projectType, request.getSaleId(), request.getRefundAmt()));
    }

    @POST
    @Path("/send/cancel")
    public ExternalServiceResponse<MusicShopEvent> sendShopCancelEvent(@QueryParam("projType") ProjectType projectType,
                                                       @NotNull CancelRequest request) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopCancelEvent(projectType, request.getLessonId(), request.getRefundAmt()));
    }

    @POST
    @Path("/send/sold")
    public ExternalServiceResponse<MusicShopEvent> sendShopSoldEvent(@QueryParam("projType") ProjectType projectType,
                                                     @NotNull UUID saleId) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopSoldEvent(projectType, saleId));
    }

    @POST
    @Path("/send/scheduled")
    public ExternalServiceResponse<MusicShopEvent> sendShopScheduledEvent(@QueryParam("projType") ProjectType projectType,
                                                          @NotNull UUID lessonId) {
        return ExternalServiceResponse.success(shopEventProducer.publishMusicShopSoldEvent(projectType, lessonId));
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
