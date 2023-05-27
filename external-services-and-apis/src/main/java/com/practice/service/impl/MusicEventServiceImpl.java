package com.practice.service.impl;

import com.practice.kafka.events.MusicShopEvents;
import com.practice.kafka.model.*;
import com.practice.service.MusicEventService;
import com.practice.service.SeedService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class MusicEventServiceImpl implements MusicEventService {

    @Inject
    SeedService seedService;

    @Override
    public MusicShopEvent createSaleEvent() {
        var instrumentWithCode = seedService.getRandomInstrumentWithCode();

        var saleAmt = (double) seedService.getRandomNumInRange(100, 10000);
        var taxAmt = saleAmt * .07;
        var grandTotal = saleAmt + taxAmt;

        return MusicShopEvent.builder()
                .header(
                        EventHeader.builder()
                                .eventId(UUID.randomUUID())
                                .eventTimestamp(LocalDateTime.now())
                                .version("1.0")
                                .eventType(MusicShopEvents.sale.name())
                                .build()
                )
                .shop(
                        EventShop.builder()
                                .shopId(UUID.randomUUID())
                                .country("US")
                                .state("TX")
                                .name(seedService.getRandomShopName())
                                .build()
                )
                .sale(
                        EventSale.builder()
                                .saleId(UUID.randomUUID())
                                .instrument(instrumentWithCode.getLeft())
                                .instrumentCode(instrumentWithCode.getRight())
                                .manufactureNumber(seedService.getRandomManufactureNumber())
                                .employeeName(seedService.getRandomFullName())
                                .customerName(seedService.getRandomFullName())
                                .saleDate(LocalDateTime.now())
                                .saleAmt(saleAmt)
                                .tax(taxAmt)
                                .promoCode("")
                                .discountAmt(0d)
                                .grandTotal(grandTotal)
                                .build()
                )
                .build();
    }

    @Override
    public MusicShopEvent createLessonEvent() {
        var instrumentWithCode = seedService.getRandomInstrumentWithCode();

        var saleAmt = (double) seedService.getRandomNumInRange(10, 201);
        var taxAmt = saleAmt * .07;
        var grandTotal = saleAmt + taxAmt;

        return MusicShopEvent.builder()
                .header(
                        EventHeader.builder()
                                .eventId(UUID.randomUUID())
                                .eventTimestamp(LocalDateTime.now())
                                .version("1.0")
                                .eventType(MusicShopEvents.lesson.name())
                                .build()
                )
                .shop(
                        EventShop.builder()
                                .shopId(UUID.randomUUID())
                                .country("US")
                                .state("TX")
                                .name(seedService.getRandomShopName())
                                .build()
                )
                .lesson(
                        EventLesson.builder()
                                .lessonId(UUID.randomUUID())
                                .instrument(instrumentWithCode.getLeft())
                                .teacherName(seedService.getRandomFullName())
                                .customerName(seedService.getRandomFullName())
                                .lessonDate(LocalDateTime.now().plusDays(seedService.getRandomNumInRange(2, 8)))
                                .saleAmt(saleAmt)
                                .tax(taxAmt)
                                .promoCode("")
                                .discountAmt(0d)
                                .grandTotal(grandTotal)
                                .build()
                )
                .build();
    }

    @Override
    public MusicShopEvent createReturnEvent(UUID saleId, double refundAmt) {
        return MusicShopEvent.builder()
                .header(
                        EventHeader.builder()
                                .eventId(UUID.randomUUID())
                                .eventTimestamp(LocalDateTime.now())
                                .version("1.0")
                                .eventType(MusicShopEvents.returns.name())
                                .build()
                )
                .returns(
                        EventReturn.builder()
                                .saleId(saleId)
                                .returnDate(LocalDateTime.now())
                                .refundAmt(refundAmt)
                                .build()
                )
                .build();
    }

    @Override
    public MusicShopEvent createCancelEvent(UUID lessonId, double refundAmt) {
        return MusicShopEvent.builder()
                .header(
                        EventHeader.builder()
                                .eventId(UUID.randomUUID())
                                .eventTimestamp(LocalDateTime.now())
                                .version("1.0")
                                .eventType(MusicShopEvents.cancel.name())
                                .build()
                )
                .cancel(
                        EventCancel.builder()
                                .lessonId(lessonId)
                                .refundAmt(refundAmt)
                                .build()
                )
                .build();
    }

    @Override
    public MusicShopEvent createSoldEvent(UUID saleId) {
        var saleEvent = createSaleEvent();
        saleEvent.getHeader().setEventType(MusicShopEvents.sold.name());
        saleEvent.getSale().setSaleId(saleId);

        return saleEvent;
    }

    @Override
    public MusicShopEvent createScheduledEvent(UUID lessonId) {
        var lessonEvent = createLessonEvent();
        lessonEvent.getHeader().setEventType(MusicShopEvents.scheduled.name());
        lessonEvent.getLesson().setLessonId(lessonId);

        return lessonEvent;
    }
}
