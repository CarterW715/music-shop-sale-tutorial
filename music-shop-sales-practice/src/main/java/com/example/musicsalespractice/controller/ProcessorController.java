package com.example.musicsalespractice.controller;

import com.practice.musicsalesgood.factory.KafkaProcessorFactory;
import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.kafka.processor.KafkaProcessor;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Produces("application/json")
@Path("/v1/kafka/processor")
@RegisterForReflection
public class ProcessorController {

//    @Inject
//    Inject our factory here

    @GET
    @Path("/names")
    public RestResponse<List<String>> getListenerNames() {
        // TODO:: fill in to get listener names

        return RestResponse.ok();
    }

    @POST
    @Path("/{listener}/run")
    public RestResponse<String> runProcessor(@PathParam("listener") String listener, @NonNull MusicShopEvent event) {
        var processor =
                kafkaProcessorFactoryImpl.getKafkaProcessors().stream().filter(l -> l.getListenerName().equalsIgnoreCase(listener)).findFirst();

        if (processor.isPresent()) {
            try {
                processor.get().handleEvent(event);
            } catch (Exception ex) {
                return RestResponse.status(400, "Error processing message. Check logs for details");
            }
            return RestResponse.status(200, "successfully submitted event. check logs details");
        }

        return RestResponse.status(404, "listener not found");
    }
}
