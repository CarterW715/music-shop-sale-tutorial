package com.practice.musicsalespractice.repository;

import com.practice.musicsalespractice.repository.model.LessonCancel;
import com.practice.musicsalespractice.repository.model.ShopLesson;
import com.practice.musicsalespractice.repository.model.ShopReturn;
import com.practice.musicsalespractice.repository.model.ShopSale;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ShopTransactionRepository {

    @Transactional
    void saveShopSale(ShopSale data);

    Optional<ShopSale> getSaleBySaleId(UUID saleId);

    void saveShopLesson(ShopLesson data);

    Optional<ShopLesson> getLessonByLessonId(UUID lessonId);

    void saveLessonCancel(LessonCancel data);

    Optional<LessonCancel> getCancelByLessonId(UUID saleId);

    void saveShopReturn(ShopReturn data);

    Optional<ShopReturn> getReturnBySaleId(UUID saleId);

}
