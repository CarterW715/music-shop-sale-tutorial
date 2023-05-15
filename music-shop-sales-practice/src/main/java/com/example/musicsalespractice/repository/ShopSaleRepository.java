package com.example.musicsalespractice.repository;

import com.example.musicsalespractice.repository.model.ShopLesson;
import com.example.musicsalespractice.repository.model.ShopSale;
import com.practice.musicsalesgood.repository.model.ShopSale;

import java.util.Optional;
import java.util.UUID;

public interface ShopTransactionRepository {

    void saveShopSale(ShopSale data);

    Optional<ShopSale> getSaleBySaleId(UUID saleId);

    void saveShopLesson(ShopLesson data);

    Optional<ShopLesson> getLessonByLessonId(UUID lessonId);

}
