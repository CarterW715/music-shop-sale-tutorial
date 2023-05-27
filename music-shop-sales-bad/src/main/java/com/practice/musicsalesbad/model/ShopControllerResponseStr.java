package com.practice.musicsalesbad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ShopControllerResponseStr {
    String data;
    Status status;

    ShopControllerResponseStr(String data, boolean success, int code, String message) {
        this.data = data;
        this.status = new Status(success, code, message);
    }

    public static ShopControllerResponseStr success() {
        return new ShopControllerResponseStr(null, true, 200, "");
    }

    public static ShopControllerResponseStr success(String data) {
        return new ShopControllerResponseStr(data, true, 200, "");
    }

    public static ShopControllerResponseStr success(String data, String message) {
        return new ShopControllerResponseStr(data, true, 200, message);
    }

    public static ShopControllerResponseStr created(String data) {
        return new ShopControllerResponseStr(data, true, 201, "");
    }

    public static ShopControllerResponseStr failed(String message) {
        return new ShopControllerResponseStr(null, false, 400, message);
    }

    public static ShopControllerResponseStr notFound() {
        return new ShopControllerResponseStr(null, false, 404, "Data could not be found");
    }

    @Getter
    @AllArgsConstructor
    public static class Status {
        boolean success;
        int code;
        String message;
    }
}
