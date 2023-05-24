package com.practice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Warranty {

    Instrument instrumentDetails;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String warrantyName;
    String warrantyCode;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Instrument {
        UUID saleId;
        String instrumentName;
        String instrumentCode;
        String manufactureCode;
    }

}
