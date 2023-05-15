package com.practice.musicsalesgood.service.rest;

import com.practice.musicsalesgood.service.rest.model.WarrantySubmitRequest;
import com.practice.musicsalesgood.service.rest.model.WarrantySubmitResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;

@RegisterRestClient(configKey = "warranty-api")
public interface WarrantyServiceRest {

    @Path("/external/warranty/submit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    RestResponse<WarrantySubmitResponse> submitWarranty(WarrantySubmitRequest request);

}
