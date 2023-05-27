package com.practice.musicsalespractice.controller;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.stream.Collectors;

// TODO :: Implement our api for our kafka event processes
@Slf4j
@Produces("application/json")
//@Path("/v1/kafka/processor") ***Uncomment when ready to create endpoints***
@RegisterForReflection
public class ProcessorController {

//    @Inject
//    {{Inject our factory here}}

//    @GET
//    @Path("/names")
//    public {{Controller response type here}} getListenerNames() {
//        {{Code to get processor names here}}
//
//        return RestResponse.ok();
//    }

//    @POST
//    @Path("/{listener}/run")
//    public {{Controller response type here}} runProcessor(@PathParam("listener") String listener, @NonNull MusicShopEvent event) {
//          {{Code to run specified processor here}}
//
//         return RestResponse.ok();
//    }
}
