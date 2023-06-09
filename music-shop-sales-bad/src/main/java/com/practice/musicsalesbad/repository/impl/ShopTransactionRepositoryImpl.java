package com.practice.musicsalesbad.repository.impl;

import com.practice.musicsalesbad.repository.ShopTransactionRepository;
import com.practice.musicsalesbad.repository.model.LessonCancel;
import com.practice.musicsalesbad.repository.model.ShopLesson;
import com.practice.musicsalesbad.repository.model.ShopReturn;
import com.practice.musicsalesbad.repository.model.ShopSale;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ShopTransactionRepositoryImpl implements ShopTransactionRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void saveShopSale(ShopSale data) {
        entityManager.persist(data);
    }

    @Transactional
    public void saveShopLesson(ShopLesson data) {
        entityManager.persist(data);
    }

    @ActivateRequestContext
    public Optional<ShopSale> getSaleBySaleId(UUID saleId) {
        var query = entityManager
                .createQuery("Select sale from ShopSale sale where sale.saleId = :targetId", ShopSale.class);

        query.setParameter("targetId", saleId);

        return query.getResultStream().findFirst();
    }

    @ActivateRequestContext
    public Optional<ShopLesson> getLessonByLessonId(UUID lessonId) {
        var query = entityManager
                .createQuery("Select lesson from ShopLesson lesson where lesson.lessonId = :targetId", ShopLesson.class);

        query.setParameter("targetId", lessonId);

        return query.getResultStream().findFirst();
    }

    public List<ShopSale> getMusicSales() {
        return entityManager
                .createQuery("Select sale from ShopSale sale", ShopSale.class)
                .getResultList();
    }

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
    public Optional<LessonCancel> getCancelByLessonId(UUID saleId) {
        var query = entityManager
                .createQuery("Select return from LessonCancel return where return.shopLesson.lessonId = :targetId", LessonCancel.class);

        query.setParameter("targetId", saleId);

        return query.getResultStream().findFirst();
    }
}
