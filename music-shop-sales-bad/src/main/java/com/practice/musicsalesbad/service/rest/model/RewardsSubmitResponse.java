package com.practice.musicsalesbad.service.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Data
@AllArgsConstructor
public class RewardsSubmitResponse {
    String message;
}
