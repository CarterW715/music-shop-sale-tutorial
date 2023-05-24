package com.practice.musicsalesgood.mapper;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.repository.model.LessonCancel;
import com.practice.musicsalesgood.repository.model.ShopLesson;
import com.practice.musicsalesgood.repository.model.ShopReturn;
import com.practice.musicsalesgood.repository.model.ShopSale;
import com.practice.musicsalesgood.service.rest.model.RewardsSubmitRequest;
import com.practice.musicsalesgood.service.rest.model.WarrantySubmitRequest;

public class MessageMapper {

    public static ShopSale messageToShopSale(MusicShopEvent message) {
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
                .tax(sale.getTax())
                .shopCountry(shop.getCountry())
                .shopName(shop.getName())
                .shopState(shop.getState())
                .build();
    }

    public static ShopLesson messageToShopLesson(MusicShopEvent message) {
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

    public static ShopReturn messageToSaleReturn(MusicShopEvent message, ShopSale sale) {
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

    public static LessonCancel messageToLessonCancel(MusicShopEvent message, ShopLesson lesson) {
        var header = message.getHeader();
        var returns = message.getReturns();
        return LessonCancel.builder()
                .shopLesson(lesson)
                .messageId(header.getMessageId())
                .version(header.getVersion())
                .eventTimestamp(header.getEventTimestamp())
                .refundAmount(returns.getRefundAmt())
                .build();
    }

    public static WarrantySubmitRequest messageToWarrantyRequest(MusicShopEvent message) {
        var sale = message.getSale();
        return WarrantySubmitRequest.builder()
                .saleId(sale.getSaleId())
                .instrumentName(sale.getInstrument())
                .instrumentCode(sale.getInstrumentCode())
                .manufactureCode(sale.getManufactureNumber())
                .build();
    }

    public static RewardsSubmitRequest messageToRewardsRequest(MusicShopEvent message) {
        var request = RewardsSubmitRequest.builder().build();
        if (message.getSale() != null) {
            request.setEmployeeName(message.getSale().getEmployeeName());
        } else {
            request.setTeacherName(message.getLesson().getTeacherName());
        }
        return request;
    }
}
