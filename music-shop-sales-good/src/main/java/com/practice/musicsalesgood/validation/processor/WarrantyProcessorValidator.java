package com.practice.musicsalesgood.validation.processor;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class WarrantyProcessorValidator extends ProcessorValidator {

    ShopSaleRepository shopTransactionRepositoryImpl;

    @Override
    public void validationLogic(MusicShopEvent event, List<String> errorList) {
        var sale = event.getSale();

        if (sale.getSaleId() == null) {
            errorList.add("Sale Id is required");
        } else {
            shopTransactionRepositoryImpl
                    .getSaleBySaleId(sale.getSaleId())
                    .ifPresentOrElse(shopSale -> {}, () -> errorList.add("A sale with this id has already been recorded"));
        }
    }
}
