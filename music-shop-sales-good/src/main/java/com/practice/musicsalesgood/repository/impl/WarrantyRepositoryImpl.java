package com.practice.musicsalesgood.repository.impl;

import com.practice.musicsalesgood.repository.WarrantyRepository;
import com.practice.musicsalesgood.repository.model.SaleWarranty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class WarrantyRepositoryImpl implements WarrantyRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void saveWarranty(SaleWarranty data) {
        entityManager.persist(data);
    }

}
