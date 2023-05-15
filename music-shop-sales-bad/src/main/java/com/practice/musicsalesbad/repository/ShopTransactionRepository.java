package com.practice.musicsalesbad.repository;

import com.practice.musicsalesbad.repository.model.LessonCancel;
import com.practice.musicsalesbad.repository.model.ShopLesson;
import com.practice.musicsalesbad.repository.model.ShopReturn;
import com.practice.musicsalesbad.repository.model.ShopSale;

import java.util.Optional;
import java.util.UUID;

public interface ShopTransactionRepository {

    void saveShopSale(ShopSale data);

    Optional<ShopSale> getSaleBySaleId(UUID saleId);

    void saveShopLesson(ShopLesson data);

    Optional<ShopLesson> getLessonByLessonId(UUID lessonId);

    void saveLessonCancel(LessonCancel data);

    Optional<LessonCancel> getCancelByLessonId(UUID saleId);

    void saveShopReturn(ShopReturn data);

    Optional<ShopReturn> getReturnBySaleId(UUID saleId);

}
