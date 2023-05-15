package com.practice.musicsalesgood.repository;

import com.practice.musicsalesgood.repository.model.LessonCancel;
import com.practice.musicsalesgood.repository.model.ShopReturn;

import java.util.Optional;
import java.util.UUID;

public interface LessonCancelRepository {

    void saveLessonCancel(LessonCancel data);

    Optional<LessonCancel> getCancelByLessonId(UUID saleId);

}
