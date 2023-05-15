package com.practice.musicsalesbad.repository;

import com.practice.musicsalesgood.repository.model.LessonCancel;

import java.util.Optional;
import java.util.UUID;

public interface LessonCancelRepository {

    void saveLessonCancel(LessonCancel data);

    Optional<LessonCancel> getCancelByLessonId(UUID saleId);

}
