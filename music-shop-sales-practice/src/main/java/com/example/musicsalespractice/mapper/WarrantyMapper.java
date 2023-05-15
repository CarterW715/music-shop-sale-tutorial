package com.example.musicsalespractice.mapper;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.repository.model.SaleWarranty;
import com.practice.musicsalesgood.service.rest.model.WarrantySubmitResponse;

public class WarrantyMapper {

    public static SaleWarranty WarrantyResponseToEntity(WarrantySubmitResponse response, MusicShopEvent event) {
        var header = event.getHeader();
        return SaleWarranty.builder()
                .version(header.getVersion())
                .eventTimestamp(header.getEventTimestamp())
                .messageId(header.getMessageId())
                .warrantyCode(response.getWarrantyCode())
                .warrantName(response.getWarrantyName())
                .warrantyStart(response.getStartDate())
                .warrantyEnd(response.getEndDate())
                .build();
    }

}
