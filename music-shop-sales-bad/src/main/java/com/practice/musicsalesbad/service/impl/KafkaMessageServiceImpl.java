package com.practice.musicsalesbad.service.impl;

import com.practice.musicsalesbad.kafka.model.MusicShopEvent;
import com.practice.musicsalesbad.mapper.MessageMapper;
import com.practice.musicsalesbad.repository.ShopTransactionRepository;
import com.practice.musicsalesbad.service.KafkaMessageService;
import com.practice.musicsalesbad.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class KafkaMessageServiceImpl implements KafkaMessageService {

    @Inject
    ShopTransactionRepository shopTransactionRepositoryImpl;

    @Override
    public void handleSaleMessage(MusicShopEvent event) {
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

        if (sale.getSaleAmt() >= 0) {
            log.error("Sale amount must be greater than 0");
            return;
        }

        var musicSale = MessageMapper.MessageToShopSale(event);

        try {
            shopTransactionRepositoryImpl.saveShopSale(musicSale);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }
    }

    @Override
    public void handleLessonMessage(MusicShopEvent event) {
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

        if (lesson.getSaleAmt() >= 0) {
            log.error("Sale amount must be greater than 0");
            return;
        }

        var lessonSale = MessageMapper.MessageToShopLesson(event);

        try {
            shopTransactionRepositoryImpl.saveShopLesson(lessonSale);
        } catch (Exception ex) {
            log.error("Something went wrong", ex);
        }
    }
}
