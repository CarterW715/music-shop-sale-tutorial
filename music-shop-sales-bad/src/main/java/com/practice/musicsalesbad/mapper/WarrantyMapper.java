package com.practice.musicsalesbad.mapper;


import com.practice.musicsalesbad.kafka.model.MusicShopEvent;
import com.practice.musicsalesbad.repository.model.SaleWarranty;
import com.practice.musicsalesbad.repository.model.ShopSale;
import com.practice.musicsalesbad.service.rest.model.WarrantySubmitResponse;

public class WarrantyMapper {

    public static SaleWarranty warrantyResponseToEntity(WarrantySubmitResponse response, MusicShopEvent event, ShopSale sale) {
        var header = event.getHeader();
        return SaleWarranty.builder()
                .version(header.getVersion())
                .eventTimestamp(header.getEventTimestamp())
                .messageId(header.getMessageId())
                .warrantyCode(response.getWarrantyCode())
                .warrantName(response.getWarrantyName())
                .warrantyStart(response.getStartDate())
                .warrantyEnd(response.getEndDate())
                .shopSale(sale)
                .build();
    }

}
