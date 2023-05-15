package com.example.musicsalespractice.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class WarrantySubmitRequest {
    UUID saleId;
    String instrumentName;
    String instrumentCode;
    String manufactureCode;
}
