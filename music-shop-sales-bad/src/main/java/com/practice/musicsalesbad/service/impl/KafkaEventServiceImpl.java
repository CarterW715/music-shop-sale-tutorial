package com.practice.musicsalesbad.service.impl;

import com.practice.musicsalesbad.kafka.model.MusicShopEvent;
import com.practice.musicsalesbad.kafka.producer.KafkaEventProducer;
import com.practice.musicsalesbad.mapper.EventMapper;
import com.practice.musicsalesbad.mapper.WarrantyMapper;
import com.practice.musicsalesbad.repository.ShopTransactionRepository;
import com.practice.musicsalesbad.repository.WarrantyRepository;
import com.practice.musicsalesbad.service.KafkaEventService;
import com.practice.musicsalesbad.service.rest.RewardsServiceRest;
import com.practice.musicsalesbad.service.rest.WarrantyServiceRest;
import com.practice.musicsalesbad.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDateTime;

@Slf4j
@ApplicationScoped
public class KafkaEventServiceImpl implements KafkaEventService {

    @Inject
    ShopTransactionRepository shopTransactionRepositoryImpl;

    @Inject
    WarrantyRepository warrantyRepositoryImpl;

    @RestClient
    WarrantyServiceRest warrantyServiceRest;

    @RestClient
    RewardsServiceRest rewardsServiceRest;

    @Inject
    KafkaEventProducer kafkaEventProducer;

    @Override
    public void handleSaleEvent(MusicShopEvent event) {
        var sale = event.getSale();

        if (sale.getSaleId() == null) {
            log.error("Sale Id is required");
        }

        if (StringUtil.isEmptyOrNull(sale.getInstrument())) {
            log.error("Instrument is required");
            return;
        }

        if (StringUtil.isEmptyOrNull(sale.getManufactureNumber())) {
            log.error("Manufacture Number is required");
            return;
        }

        if (StringUtil.isEmptyOrNull(sale.getCustomerName())) {
            log.error("Customer name is required");
            return;
        }

        if (sale.getSaleDate() == null) {
            log.error("Sale Date is required");
            return;
        }

        if (sale.getSaleAmt() <= 0) {
            log.error("Sale amount must be greater than 0");
            return;
        }

        var musicSale = EventMapper.eventToShopSale(event);

        try {
            shopTransactionRepositoryImpl.saveShopSale(musicSale);
            log.info("Successfully submitted a sale");
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

        kafkaEventProducer.publishSoldMessage(event);
    }

    @Override
    public void handleLessonEvent(MusicShopEvent event) {
        var lesson = event.getLesson();

        if (lesson.getLessonId() == null) {
            log.error("Lesson Id is required");
            return;
        }

        if (StringUtil.isEmptyOrNull(lesson.getInstrument())) {
            log.error("Instrument is required");
            return;
        }

        if (StringUtil.isEmptyOrNull(lesson.getTeacherName())) {
            log.error("Teacher Name is required");
            return;
        }

        if (StringUtil.isEmptyOrNull(lesson.getCustomerName())) {
            log.error("Customer name is required");
            return;
        }

        if (lesson.getLessonDate() == null) {
            log.error("Lesson Date is required");
            return;
        }

        if (lesson.getSaleAmt() <= 0) {
            log.error("Sale amount must be greater than 0");
            return;
        }

        var lessonSale = EventMapper.eventToShopLesson(event);

        try {
            shopTransactionRepositoryImpl.saveShopLesson(lessonSale);
            log.info("Successfully scheduled lesson");
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

        kafkaEventProducer.publishScheduledMessage(event);
    }

    @Override
    public void handleReturnEvent(MusicShopEvent event) {
        var shopReturn = event.getReturns();

        if (shopReturn.getSaleId() == null) {
            log.error("Sale Id is required");
            return;
        }

        if (shopReturn.getReturnDate() == null) {
            log.error("Instrument is required");
            return;
        }

        if (shopReturn.getRefundAmt() == null) {
            log.error("Refund amount is required");
            return;
        }

        if (shopReturn.getRefundAmt() < 0) {
            log.error("Refund amount cannot be less than 0");
            return;
        }

        var shopSale = shopTransactionRepositoryImpl.getSaleBySaleId(shopReturn.getSaleId());
        if (shopSale.isEmpty()) {
            log.error("No sale record can be found for return");
            return;
        }

        var returnFound = shopTransactionRepositoryImpl.getReturnBySaleId(shopReturn.getSaleId());
        if (returnFound.isPresent()) {
            log.error("A return with this sale id has already been recorded");
            return;
        }

        if (shopReturn.getReturnDate().isBefore(shopSale.get().getSaleDate())) {
            log.error("Return date cannot be before sale date");
        }

        if (shopReturn.getRefundAmt() > shopSale.get().getSaleAmount()) {
            log.error("Refund amount cannot be more than the sale amount");
        }

        var returned = EventMapper.eventToSaleReturn(event, shopSale.get());

        try {
            shopTransactionRepositoryImpl.saveShopReturn(returned);
            log.info("Successfully returned sale");
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

        kafkaEventProducer.publishReturnedMessage(event);
    }

    public void handleCancelEvent(MusicShopEvent event) {
        var cancel = event.getCancel();

        if (cancel.getLessonId() == null) {
            log.error("Sale Id is required");
            return;
        }

        if (cancel.getRefundAmt() == null) {
            log.error("Refund amount is required");
            return;
        }

        if (cancel.getRefundAmt() < 0) {
            log.error("Refund amount cannot be less than 0");
            return;
        }

        var shopLesson = shopTransactionRepositoryImpl.getLessonByLessonId(cancel.getLessonId());
        if (!shopLesson.isPresent()) {
            log.error("The lesson with id: {} could not be found to be canceled", cancel.getLessonId());
            return;
        }

        if (LocalDateTime.now().isAfter(shopLesson.get().getLessonDate())) {
            log.error("Cancel date cannot be after lesson date");
        }

        if (cancel.getRefundAmt() > shopLesson.get().getSaleAmount()) {
            log.error("Refund amount cannot be more than the sale amount");
        }

        var currentCancel = shopTransactionRepositoryImpl.getCancelByLessonId(cancel.getLessonId());
        if (currentCancel.isPresent()) {
            log.error("A cancellation with this lesson id has already been recorded");
            return;
        }

        var lessonCancel = EventMapper.eventToLessonCancel(event, shopLesson.get());

        try {
            shopTransactionRepositoryImpl.saveLessonCancel(lessonCancel);
            log.info("Successfully canceled lesson");
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }

        kafkaEventProducer.publishCanceledMessage(event);
    }

    public void handleWarrantyEvent(MusicShopEvent event) {
        var sale = event.getSale();

        if (sale.getSaleId() == null) {
            log.error("Sale Id is required");
            return;
        }

        var shopSale = shopTransactionRepositoryImpl.getSaleBySaleId(sale.getSaleId());
        if (shopSale.isEmpty()) {
            log.error("A sale with this id has already been recorded");
            return;
        }

        var request = EventMapper.eventToWarrantyRequest(event);

        try {
            var response = warrantyServiceRest.submitWarranty(request);
            if (response.getStatus().getCode() != 200) {
                log.error("Something went wrong: {}", response.getStatus().getMessage());
            } else {
                var warranty = WarrantyMapper.warrantyResponseToEntity(response.getData(), event, shopSale.get());
                warrantyRepositoryImpl.saveWarranty(warranty);
                log.info("Successfully activated warranty");
            }
        } catch (WebApplicationException ex) {
            log.error("Could not submit warranty successfully", ex);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }
    }

    public void handleRewardsEvent(MusicShopEvent event) {
        if (event.getSale() != null) {
            if (StringUtil.isEmptyOrNull(event.getSale().getEmployeeName())) {
                log.error("Employee Name or Teacher Name is required");
            }
        } else if (event.getLesson() != null) {
            if (StringUtil.isEmptyOrNull(event.getLesson().getTeacherName())) {
                log.error("Employee Name or Teacher Name is required");
            }
        } else {
            log.error("Employee Name or Teacher Name is required");
        }

        var request = EventMapper.eventToRewardsRequest(event);

        var response = rewardsServiceRest.submitRewards(request);
        if (response.getStatus().getCode() != 200) {
            log.error("Something went wrong: {}", response.getStatus().getMessage());
            return;
        }

        log.info("Successfully submitted rewards");
    }
}
