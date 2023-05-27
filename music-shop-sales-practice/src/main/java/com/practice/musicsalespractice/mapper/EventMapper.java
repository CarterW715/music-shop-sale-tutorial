package com.practice.musicsalespractice.mapper;

import com.practice.musicsalespractice.kafka.model.MusicShopEvent;
import com.practice.musicsalespractice.repository.model.LessonCancel;
import com.practice.musicsalespractice.repository.model.ShopLesson;
import com.practice.musicsalespractice.repository.model.ShopReturn;
import com.practice.musicsalespractice.repository.model.ShopSale;

public class EventMapper {

    public static ShopSale eventToShopSale(MusicShopEvent event) {
        var header = event.getHeader();
        var shop = event.getShop();
        var sale = event.getSale();
        return ShopSale.builder()
                .saleId(sale.getSaleId())
                .messageId(header.getEventId())
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

    public static ShopLesson eventToShopLesson(MusicShopEvent event) {
        var header = event.getHeader();
        var shop = event.getShop();
        var lesson = event.getLesson();
        return ShopLesson.builder()
                .lessonId(lesson.getLessonId())
                .messageId(header.getEventId())
                .shopId(shop.getShopId())
                .customerName(lesson.getCustomerName())
                .teacherName(lesson.getTeacherName())
                .eventTimestamp(header.getEventTimestamp())
                .discountAmount(lesson.getDiscountAmt())
                .grandTotal(lesson.getGrandTotal())
                .instrument(lesson.getInstrument())
                .messageVersion(header.getVersion())
                .promoCode(lesson.getPromoCode())
                .tax(lesson.getTax())
                .saleAmount(lesson.getSaleAmt())
                .lessonDate(lesson.getLessonDate())
                .shopCountry(shop.getCountry())
                .shopName(shop.getName())
                .shopState(shop.getState())
                .build();
    }

    public static ShopReturn eventToSaleReturn(MusicShopEvent event, ShopSale sale) {
        var header = event.getHeader();
        var returns = event.getReturns();
        return ShopReturn.builder()
                .shopSale(sale)
                .messageId(header.getEventId())
                .version(header.getVersion())
                .eventTimestamp(header.getEventTimestamp())
                .refundAmount(returns.getRefundAmt())
                .returnDate(returns.getReturnDate())
                .build();
    }

    public static LessonCancel eventToLessonCancel(MusicShopEvent event, ShopLesson lesson) {
        var header = event.getHeader();
        var cancel = event.getCancel();
        return LessonCancel.builder()
                .shopLesson(lesson)
                .messageId(header.getEventId())
                .version(header.getVersion())
                .eventTimestamp(header.getEventTimestamp())
                .refundAmount(cancel.getRefundAmt())
                .build();
    }
}
