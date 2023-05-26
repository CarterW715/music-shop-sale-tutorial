package com.practice.musicsalesgood.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SalesControllerResponse<T> {
    T data;
    Status status;

    SalesControllerResponse(T data, boolean success, int code, String message) {
        this.data = data;
        this.status = new Status(success, code, message);
    }

    public static <T> SalesControllerResponse<T> success() {
        return new SalesControllerResponse<>(null, true, 200, "");
    }

    public static <T> SalesControllerResponse<T> success(T data) {
        return new SalesControllerResponse<>(data, true, 200, "");
    }

    public static <T> SalesControllerResponse<T> success(T data, String message) {
        return new SalesControllerResponse<>(data, true, 200, message);
    }

    public static <T> SalesControllerResponse<T> created(T data) {
        return new SalesControllerResponse(data, true, 201, "");
    }

    public static <T> SalesControllerResponse<T> failed(String message) {
        return new SalesControllerResponse<>(null, false, 400, message);
    }

    public static <T> SalesControllerResponse<T> notFound() {
        return new SalesControllerResponse<>(null, false, 404, "Data could not be found");
    }

    @Getter
    @AllArgsConstructor
    public static class Status {
        boolean success;
        int code;
        String message;
    }
}
