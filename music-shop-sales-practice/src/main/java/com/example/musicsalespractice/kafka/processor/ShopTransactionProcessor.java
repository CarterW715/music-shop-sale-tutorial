package com.example.musicsalespractice.kafka.processor;

import com.example.musicsalespractice.kafka.events.MusicShopEvents;
import com.example.musicsalespractice.kafka.model.MusicShopEvent;
import com.example.musicsalespractice.kafka.producer.MusicShopEventProducer;
import com.example.musicsalespractice.mapper.MessageMapper;
import com.example.musicsalespractice.repository.ShopTransactionRepository;
import com.example.musicsalespractice.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationScoped
public class ShopTransactionProcessor {

    @Inject
    ShopTransactionRepository shopTransactionRepositoryImpl;

    @Inject
    MusicShopEventProducer musicShopEventProducer;

    List<String> checkErrors(MusicShopEvent event) {
        var errorList = new ArrayList<String>();

        if (event.getHeader().getEventType().equals(MusicShopEvents.sale.name())) {
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

            if (sale.getSaleAmt() >= 0) {
                errorList.add("Sale amount must be greater than 0");
            }
        }

        if (event.getHeader().getEventType().equals(MusicShopEvents.lesson.name())) {
            var lesson = event.getLesson();

            if (lesson.getLessonId() == null) {
                errorList.add("Lesson Id is required");
            } else {
                shopLessonRepositoryImpl
                        .getLessonByLessonId(lesson.getLessonId())
                        .ifPresent(shopLesson -> errorList.add("A lesson with this id has already been recorded"));
            }

            if (StringUtil.isEmptyOrNull(lesson.getInstrument())) {
                errorList.add("Instrument is required");
            }

            if (StringUtil.isEmptyOrNull(lesson.getTeacherName())) {
                errorList.add("Teacher Name is required");
            }

            if (StringUtil.isEmptyOrNull(lesson.getCustomerName())) {
                errorList.add("Customer name is required");
            }

            if (lesson.getLessonDate() == null) {
                errorList.add("Lesson Date is required");
            }

            if (lesson.getSaleAmt() >= 0) {
                errorList.add("Sale amount must be greater than 0");
            }
        }

        return errorList;
    }

    public void processEvent(MusicShopEvent message) {
        var errorList = checkErrors(message);
        if (!errorList.isEmpty()) {
            log.error(String.join(", ", errorList));
            return;
        }

        if (event.getHeader().getEventType().equals(MusicShopEvents.sale.name())) {
            var musicSale = MessageMapper.MessageToShopSale(message);

            try {
                shopTransactionRepositoryImpl.saveShopSale(musicSale);
            } catch (Exception ex) {
                log.error("Something went wrong", ex);
            }

            musicShopEventProducer.publishSoldEvent(message);
        } else if (event.getHeader().getEventType().equals(MusicShopEvents.lesson.name())) {
            var lesson = MessageMapper.MessageToShopLesson(message);

            try {
                shopTransactionRepositoryImpl.saveShopLesson(lesson);
            } catch (Exception ex) {
                log.error("Something went wrong", ex);
            }

            musicShopEventProducer.publishScheduledEvent(message);
        }


    }
}
