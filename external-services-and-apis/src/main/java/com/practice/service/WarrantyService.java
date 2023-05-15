package com.practice.service;

import com.practice.model.Warranty;
import com.practice.model.request.WarrantySubmitRequest;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.Random;

@ApplicationScoped
public class WarrantyService {

    public Random RAND;

    public Warranty createWarranty(WarrantySubmitRequest request) {
        var now = LocalDateTime.now();
        return Warranty.builder()
                .instrumentDetails(
                        Warranty.Instrument.builder()
                                .instrumentName(request.getInstrumentName())
                                .instrumentCode(request.getInstrumentCode())
                                .manufactureCode(request.getManufactureCode())
                                .saleId(request.getSaleId())
                                .build()
                )
                .warrantyCode("warranty_code_" + RAND.nextInt(123))
                .warrantyName("warranty_name_" + RAND.nextInt(123))
                .startDate(now)
                .endDate(now.plusMonths(3))
                .build();
    }

}
