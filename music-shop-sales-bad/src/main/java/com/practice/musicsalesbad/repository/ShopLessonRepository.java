package com.practice.musicsalesbad.repository;

import com.practice.musicsalesgood.repository.model.ShopLesson;

import java.util.Optional;
import java.util.UUID;

public interface ShopLessonRepository {

    void saveShopLesson(ShopLesson data);

    Optional<ShopLesson> getLessonByLessonId(UUID lessonId);

}
