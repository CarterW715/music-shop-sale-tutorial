package com.practice.musicsalesgood.controller;

import com.practice.musicsalesgood.factory.KafkaProcessorFactory;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.processor.KafkaProcessor;
import com.practice.musicsalesgood.model.SalesControllerResponse;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Produces("application/json")
@Path("/v1/kafka/processor")
@RegisterForReflection
public class ProcessorController {

    @Inject
    KafkaProcessorFactory kafkaProcessorFactoryImpl;

    @GET
    @Path("/names")
    public SalesControllerResponse<List<String>> getListenerNames() {
        var listenerNames =
                kafkaProcessorFactoryImpl.getKafkaProcessors().stream().map(KafkaProcessor::getListenerName).collect(Collectors.toList());

        return SalesControllerResponse.success(listenerNames);
    }

    @POST
    @Path("/{listener}/run")
    public SalesControllerResponse<String> runProcessor(@PathParam("listener") String listener, @NonNull MusicShopEvent event) {
        var processor =
                kafkaProcessorFactoryImpl.getKafkaProcessors().stream().filter(l -> l.getListenerName().equalsIgnoreCase(listener)).findFirst();

        if (processor.isPresent()) {
            try {
                processor.get().handleEvent(event);
            } catch (Exception ex) {
                return SalesControllerResponse.failed("Error processing message. Check logs for details");
            }
            return SalesControllerResponse.success("successfully submitted event. check logs details");
        }

        return SalesControllerResponse.notFound();
    }
}
