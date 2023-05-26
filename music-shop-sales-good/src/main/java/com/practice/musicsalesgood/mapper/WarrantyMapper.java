package com.practice.musicsalesgood.mapper;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.repository.model.SaleWarranty;
import com.practice.musicsalesgood.repository.model.ShopSale;
import com.practice.musicsalesgood.service.rest.model.WarrantySubmitResponse;

public class WarrantyMapper {

    public static SaleWarranty warrantyResponseToEntity(WarrantySubmitResponse response, MusicShopEvent event, ShopSale sale) {
        var header = event.getHeader();
        return SaleWarranty.builder()
                .version(header.getVersion())
                .eventTimestamp(header.getEventTimestamp())
                .messageId(header.getEventId())
                .warrantyCode(response.getWarrantyCode())
                .warrantName(response.getWarrantyName())
                .warrantyStart(response.getStartDate())
                .warrantyEnd(response.getEndDate())
                .shopSale(sale)
                .build();
    }

}
