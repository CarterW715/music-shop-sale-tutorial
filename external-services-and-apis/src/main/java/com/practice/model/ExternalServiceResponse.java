package com.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ExternalServiceResponse<T> {
    T data;
    Status status;

    ExternalServiceResponse(T data, boolean success, int code, String message) {
        this.data = data;
        this.status = new Status(success, code, message);
    }

    public static <T> ExternalServiceResponse<T> success() {
        return new ExternalServiceResponse<>(null, true, 200, "");
    }

    public static <T> ExternalServiceResponse<T> success(T data) {
        return new ExternalServiceResponse<>(data, true, 200, "");
    }

    public static <T> ExternalServiceResponse<T> created(T data) {
        return new ExternalServiceResponse<>(data, true, 201, "");
    }

    public static <T> ExternalServiceResponse<T> failed(String message) {
        return new ExternalServiceResponse<>(null, false, 400, message);
    }

    public static <T> ExternalServiceResponse<T> notFound() {
        return new ExternalServiceResponse<>(null, false, 404, "Data could not be found");
    }

    @Getter
    @AllArgsConstructor
    public static class Status {
        boolean success;
        int code;
        String message;
    }
}
