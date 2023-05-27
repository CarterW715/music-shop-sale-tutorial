package com.practice.musicsalespractice.service.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
@AllArgsConstructor
public class RewardsSubmitResponse {
    String message;
}
