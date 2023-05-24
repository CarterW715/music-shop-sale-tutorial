package com.practice.controller;

import com.practice.model.ExternalServiceResponse;
import com.practice.model.request.RewardsSubmitRequest;
import com.practice.model.response.RewardsResponse;
import com.practice.service.RewardsService;
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
@Path("/external/rewards")
@RegisterForReflection
@ApplicationScoped
public class RewardsController {

    @Inject
    RewardsService rewardsService;

    @POST
    @Path("/submit")
    @Produces(MediaType.APPLICATION_JSON)
    public ExternalServiceResponse processRewardsSubmission(@NonNull RewardsSubmitRequest request) {
        return ExternalServiceResponse.success(rewardsService.processRewards(request));
    }

}
