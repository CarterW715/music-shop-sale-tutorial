package com.practice.controller;

import com.practice.model.Warranty;
import com.practice.model.request.WarrantySubmitRequest;
import com.practice.service.WarrantyService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

@Slf4j
@Path("/external/warranty")
@RegisterForReflection
@ApplicationScoped
public class WarrantyController {

    @Inject
    WarrantyService warrantyService;

    @POST
    @Path("/submit")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<Warranty> processWarrantySubmission(@NonNull WarrantySubmitRequest request) {
        return RestResponse.ok(warrantyService.createWarranty(request));
    }

}
