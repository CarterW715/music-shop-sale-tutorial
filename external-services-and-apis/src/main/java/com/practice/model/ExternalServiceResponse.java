package com.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ExternalServiceResponse {
    Object data;
    Status status;

    ExternalServiceResponse(Object data, boolean success, int code, String message) {
        this.data = data;
        this.status = new Status(success, code, message);
    }

    public static ExternalServiceResponse success() {
        return new ExternalServiceResponse(null, true, 200, "");
    }

    public static ExternalServiceResponse success(Object data) {
        return new ExternalServiceResponse(data, true, 200, "");
    }

    public static ExternalServiceResponse created(Object data) {
        return new ExternalServiceResponse(data, true, 201, "");
    }

    public static ExternalServiceResponse failed(String message) {
        return new ExternalServiceResponse(null, false, 400, message);
    }

    public static ExternalServiceResponse notFound() {
        return new ExternalServiceResponse(null, false, 404, "Data could not be found");
    }

    @Getter
    @AllArgsConstructor
    public static class Status {
        boolean success;
        int code;
        String message;
    }
}
