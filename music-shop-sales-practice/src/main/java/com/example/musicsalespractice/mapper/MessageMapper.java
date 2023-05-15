package com.example.musicsalespractice.mapper;

import com.example.musicsalespractice.kafka.model.MusicShopEvent;
import com.example.musicsalespractice.repository.model.ShopLesson;
import com.example.musicsalespractice.repository.model.ShopSale;

public class MessageMapper {

    public static ShopSale MessageToShopSale(MusicShopEvent message) {
        var header = message.getHeader();
        var shop = message.getShop();
        var sale = message.getSale();
        return ShopSale.builder()
                .saleId(sale.getSaleId())
                .messageId(header.getMessageId())
                .shopId(shop.getShopId())
                .customerName(sale.getCustomerName())
                .employeeName(sale.getEmployeeName())
                .eventTimestamp(header.getEventTimestamp())
                .discountAmount(sale.getDiscountAmt())
                .grandTotal(sale.getGrandTotal())
                .instrument(sale.getInstrument())
                .instrumentCode(sale.getInstrumentCode())
                .manufactureNumber(sale.getManufactureNumber())
                .messageVersion(header.getVersion())
                .promoCode(sale.getPromoCode())
                .saleAmount(sale.getSaleAmt())
                .saleDate(sale.getSaleDate())
                .shopCountry(shop.getCountry())
                .shopName(shop.getName())
                .shopState(shop.getState())
                .build();
    }

    public static ShopLesson MessageToShopLesson(MusicShopEvent message) {
        var header = message.getHeader();
        var shop = message.getShop();
        var lesson = message.getLesson();
        return ShopLesson.builder()
                .lessonId(lesson.getLessonId())
                .messageId(header.getMessageId())
                .shopId(shop.getShopId())
                .customerName(lesson.getCustomerName())
                .teacherName(lesson.getTeacherName())
                .eventTimestamp(header.getEventTimestamp())
                .discountAmount(lesson.getDiscountAmt())
                .grandTotal(lesson.getGrandTotal())
                .instrument(lesson.getInstrument())
                .version(header.getVersion())
                .promoCode(lesson.getPromoCode())
                .saleAmount(lesson.getSaleAmt())
                .lessonDate(lesson.getLessonDate())
                .shopCountry(shop.getCountry())
                .shopName(shop.getName())
                .shopState(shop.getState())
                .build();
    }

    public static ShopReturn MessageToSaleReturn(MusicShopEvent message, ShopSale sale) {
        var header = message.getHeader();
        var returns = message.getReturns();
        return ShopReturn.builder()
                .shopSale(sale)
                .messageId(header.getMessageId())
                .version(header.getVersion())
                .eventTimestamp(header.getEventTimestamp())
                .refundAmount(returns.getRefundAmt())
                .returnDate(returns.getReturnDate())
                .build();
    }

    public static WarrantySubmitRequest MessageToWarrantyRequest(MusicShopEvent message) {
        var sale = message.getSale();
        return WarrantySubmitRequest.builder()
                .saleId(sale.getSaleId())
                .instrumentName(sale.getInstrument())
                .instrumentCode(sale.getInstrumentCode())
                .manufactureCode(sale.getManufactureNumber())
                .build();
    }

    public static RewardsSubmitRequest MessageToRewardsRequest(MusicShopEvent message) {
        var request = RewardsSubmitRequest.builder().build();
        if (message.getSale() != null) {
            request.setEmployeeName(message.getSale().getEmployeeName());
        } else {
            request.setTeacherName(message.getLesson().getTeacherName());
        }
        return request;
    }
}
