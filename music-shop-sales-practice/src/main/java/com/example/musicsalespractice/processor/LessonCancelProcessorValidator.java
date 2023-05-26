package com.example.musicsalespractice.processor;

import com.practice.musicsalesgood.kafka.model.MusicShopEvent;
import com.practice.musicsalesgood.repository.LessonCancelRepository;
import com.practice.musicsalesgood.repository.ShopLessonRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class LessonCancelProcessorValidator extends ProcessorValidator {

    LessonCancelRepository shopReturnCancelRepositoryImpl;

    ShopLessonRepository shopTransactionRepositoryImpl;

    @Override
    public void validationLogic(MusicShopEvent event, List<String> errorList) {
        var cancel = event.getCancel();

        boolean findLesson = true;

        if (cancel.getLessonId() == null) {
            errorList.add("Sale Id is required");
            findLesson = false;
        }

        if (cancel.getRefundAmt() == null) {
            errorList.add("Refund amount is required");
            findLesson = false;
        } else if (cancel.getRefundAmt() < 0) {
            errorList.add("Refund amount cannot be less than 0");
            findLesson = false;
        }

        if (findLesson) {
            shopReturnCancelRepositoryImpl
                    .getCancelByLessonId(cancel.getLessonId())
                    .ifPresentOrElse(
                            shopCancel -> errorList.add("A cancellation with this lesson id has already been recorded"),
                            () -> shopTransactionRepositoryImpl.getLessonByLessonId(cancel.getLessonId())
                                    .ifPresentOrElse(
                                            shopLesson -> {
                                                if (LocalDateTime.now().isAfter(shopLesson.getLessonDate())) {
                                                    errorList.add("Cancel date cannot be after lesson date");
                                                }

                                                if (cancel.getRefundAmt() > shopLesson.getSaleAmount()) {
                                                    errorList.add("Refund amount cannot be more than the sale amount");
                                                }
                                            },
                                            () -> errorList.add("No lesson record can be found for cancellation")
                                    ));
        }
    }
}
