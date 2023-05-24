package com.practice.musicsalesgood.repository.impl;

import com.practice.musicsalesgood.repository.LessonCancelRepository;
import com.practice.musicsalesgood.repository.SaleReturnRepository;
import com.practice.musicsalesgood.repository.model.LessonCancel;
import com.practice.musicsalesgood.repository.model.ShopReturn;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ShopReturnCancelRepositoryImpl implements SaleReturnRepository, LessonCancelRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void saveShopReturn(ShopReturn data) {
        entityManager.persist(data);
    }

    @ActivateRequestContext
    public Optional<ShopReturn> getReturnBySaleId(UUID saleId) {
        var query = entityManager
                .createQuery("Select return from ShopReturn return where return.shopSale.saleId = :targetId", ShopReturn.class);

        query.setParameter("targetId", saleId);

        return query.getResultStream().findFirst();
    }

    @Transactional
    public void saveLessonCancel(LessonCancel data) {
        entityManager.persist(data);
    }

    @ActivateRequestContext
    public Optional<LessonCancel> getCancelByLessonId(UUID lessonId) {
        var query = entityManager
                .createQuery("Select cancel from LessonCancel cancel where cancel.shopLesson.lessonId = :targetId", LessonCancel.class);

        query.setParameter("targetId", lessonId);

        return query.getResultStream().findFirst();
    }
}
