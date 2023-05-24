package com.practice.musicsalesgood.service.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExternalServiceResponse<T> {
    T data;
    Status status;

    @Data
    @AllArgsConstructor
    public static class Status {
        boolean success;
        int code;
        String message;
    }
}
