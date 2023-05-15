package com.practice.service;

import com.practice.model.request.RewardsSubmitRequest;
import com.practice.model.response.RewardsResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RewardsService {

    // TODO :: Add more functionality to this
    public RewardsResponse processRewards(RewardsSubmitRequest request) {
        var message = request.getEmployeeName() == null ?
                "Successfully added rewards points for teacher" :
                "Successfully added rewards points for employee";
        return RewardsResponse.builder()
                .message(message)
                .build();
    }

}
