package com.practice.musicsalesbad.service.rest;

import com.practice.musicsalesbad.service.rest.model.ExternalServiceResponse;
import com.practice.musicsalesbad.service.rest.model.RewardsSubmitRequest;
import com.practice.musicsalesbad.service.rest.model.RewardsSubmitResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;

@RegisterRestClient(configKey = "external-api")
public interface RewardsServiceRest {

    @POST
    @Path("/external/rewards/submit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ExternalServiceResponse<RewardsSubmitResponse> submitRewards(RewardsSubmitRequest request);

}
