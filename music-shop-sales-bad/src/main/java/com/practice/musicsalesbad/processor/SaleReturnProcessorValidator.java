package com.practice.musicsalesbad.processor;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.repository.SaleReturnRepository;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SaleReturnProcessorValidator extends ProcessorValidator {

    SaleReturnRepository shopReturnCancelRepositoryImpl;

    ShopSaleRepository shopTransactionRepositoryImpl;

    @Override
    public void validationLogic(MusicShopEvent event, List<String> errorList) {
        var returns = event.getReturns();

        boolean findSale = true;

        if (returns.getSaleId() == null) {
            errorList.add("Sale Id is required");
            findSale = false;
        }

        if (returns.getReturnDate() == null) {
            errorList.add("Instrument is required");
            findSale = false;
        }

        if (returns.getRefundAmt() == null) {
            errorList.add("Refund amount is required");
            findSale = false;
        } else if (returns.getRefundAmt() < 0) {
            errorList.add("Refund amount cannot be less than 0");
            findSale = false;
        }

        if (findSale) {
            shopReturnCancelRepositoryImpl
                    .getReturnBySaleId(returns.getSaleId())
                    .ifPresentOrElse(
                            shopReturn -> errorList.add("A return with this sale id has already been recorded"),
                            () -> shopTransactionRepositoryImpl.getSaleBySaleId(returns.getSaleId())
                                    .ifPresentOrElse(
                                            shopSale -> {
                                                if (returns.getReturnDate().isBefore(shopSale.getSaleDate())) {
                                                    errorList.add("Return date cannot be before sale date");
                                                }

                                                if (returns.getRefundAmt() > shopSale.getSaleAmount()) {
                                                    errorList.add("Refund amount cannot be more than the sale amount");
                                                }
                                            },
                                            () -> errorList.add("No sale record can be found for return")
                                    ));
        }
    }
}
