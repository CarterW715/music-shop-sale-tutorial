package com.practice.musicsalesgood.validation.processor;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import com.practice.musicsalesgood.util.StringUtil;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShopSaleProcessorValidator extends ProcessorValidator {

    ShopSaleRepository shopTransactionRepositoryImpl;

    @Override
    public void validationLogic(MusicShopEvent event, List<String> errorList) {
        var sale = event.getSale();

        if (sale.getSaleId() == null) {
            errorList.add("Sale Id is required");
        } else {
            shopTransactionRepositoryImpl
                    .getSaleBySaleId(sale.getSaleId())
                    .ifPresent(shopSale -> errorList.add("A sale with this id has already been recorded"));
        }

        if (StringUtil.isEmptyOrNull(sale.getInstrument())) {
            errorList.add("Instrument is required");
        }

        if (StringUtil.isEmptyOrNull(sale.getManufactureNumber())) {
            errorList.add("Manufacture Number is required");
        }

        if (StringUtil.isEmptyOrNull(sale.getCustomerName())) {
            errorList.add("Customer name is required");
        }

        if (sale.getSaleDate() == null) {
            errorList.add("Sale Date is required");
        }

        if (sale.getSaleAmt() <= 0) {
            errorList.add("Sale amount must be greater than 0");
        }
    }
}
