package com.practice.musicsalesgood.validation.processor;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.repository.ShopLessonRepository;
import com.practice.musicsalesgood.util.StringUtil;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShopLessonProcessorValidator extends ProcessorValidator {

    ShopLessonRepository shopLessonRepositoryImpl;

    @Override
    public void validationLogic(MusicShopEvent event, List<String> errorList) {
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

        if (lesson.getSaleAmt() <= 0) {
            errorList.add("Sale amount must be greater than 0");
        }
    }
}
