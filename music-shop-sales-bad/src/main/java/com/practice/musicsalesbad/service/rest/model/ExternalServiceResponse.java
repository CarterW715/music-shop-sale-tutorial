package com.practice.musicsalesbad.service.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExternalServiceWarrantyResponse {
    WarrantySubmitResponse data;
    Status status;

    @Data
    @AllArgsConstructor
    public static class Status {
        boolean success;
        int code;
        String message;
    }
}
