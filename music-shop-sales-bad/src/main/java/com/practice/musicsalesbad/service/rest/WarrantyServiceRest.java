package com.practice.musicsalesbad.service.rest;

import com.practice.musicsalesbad.service.rest.model.ExternalServiceWarrantyResponse;
import com.practice.musicsalesbad.service.rest.model.WarrantySubmitRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "external-api")
public interface WarrantyServiceRest {

    @POST
    @Path("/external/warranty/submit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ExternalServiceWarrantyResponse submitWarranty(WarrantySubmitRequest request);

}
