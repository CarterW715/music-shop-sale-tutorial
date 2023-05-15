package com.practice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class WarrantySubmitRequest {
    UUID saleId;
    String instrumentName;
    String instrumentCode;
    String manufactureCode;
}
