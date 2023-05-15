package com.practice.musicsalesgood.repository.impl;

import com.practice.musicsalesgood.repository.ShopLessonRepository;
import com.practice.musicsalesgood.repository.ShopSaleRepository;
import com.practice.musicsalesgood.repository.model.ShopLesson;
import com.practice.musicsalesgood.repository.model.ShopSale;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class ShopTransactionRepositoryImpl implements ShopSaleRepository, ShopLessonRepository {

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

    public Optional<ShopSale> getSaleBySaleId(UUID saleId) {
        var query = entityManager
                .createQuery("Select sale from ShopSale sale where sale.saleId = :targetId", ShopSale.class);

        query.setParameter("targetId", saleId);

        return Optional.of(query.getSingleResult());
    }

    public Optional<ShopLesson> getLessonByLessonId(UUID lessonId) {
        var query = entityManager
                .createQuery("Select lesson from ShopLesson lesson where lesson.lessonId = :targetId", ShopLesson.class);

        query.setParameter("targetId", lessonId);

        return Optional.of(query.getSingleResult());
    }

    public List<ShopSale> getMusicSales() {
        return entityManager
                .createQuery("Select sale from ShopSale sale", ShopSale.class)
                .getResultList();
    }
}
