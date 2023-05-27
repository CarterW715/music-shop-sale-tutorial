package com.practice.musicsalesbad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class ShopControllerResponseStrList {
    List<String> data;
    Status status;

    ShopControllerResponseStrList(List<String> data, boolean success, int code, String message) {
        this.data = data;
        this.status = new Status(success, code, message);
    }

    public static ShopControllerResponseStrList success() {
        return new ShopControllerResponseStrList(null, true, 200, "");
    }

    public static ShopControllerResponseStrList success(List<String> data) {
        return new ShopControllerResponseStrList(data, true, 200, "");
    }

    public static ShopControllerResponseStrList success(List<String> data, String message) {
        return new ShopControllerResponseStrList(data, true, 200, message);
    }

    public static ShopControllerResponseStrList created(List<String> data) {
        return new ShopControllerResponseStrList(data, true, 201, "");
    }

    public static ShopControllerResponseStrList failed(String message) {
        return new ShopControllerResponseStrList(null, false, 400, message);
    }

    public static ShopControllerResponseStrList notFound() {
        return new ShopControllerResponseStrList(null, false, 404, "Data could not be found");
    }

    @Getter
    @AllArgsConstructor
    public static class Status {
        boolean success;
        int code;
        String message;
    }
}
