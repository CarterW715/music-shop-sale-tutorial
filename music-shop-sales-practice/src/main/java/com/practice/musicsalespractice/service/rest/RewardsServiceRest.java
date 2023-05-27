package com.practice.musicsalespractice.service.rest;

import com.practice.musicsalespractice.service.rest.model.RewardsSubmitRequest;
import com.practice.musicsalespractice.service.rest.model.RewardsSubmitResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "external-api")
public interface RewardsServiceRest {

    // TODO :: Complete this rest call to the rewards api by creating (with generics) your own return structure for the call
//    @POST
//    @Path("/external/rewards/submit")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    {{add your return class here}} submitRewards(RewardsSubmitRequest request);

}

