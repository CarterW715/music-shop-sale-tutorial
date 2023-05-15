package com.practice.musicsalesbad.repository;

import com.practice.musicsalesgood.repository.model.ShopSale;

import java.util.Optional;
import java.util.UUID;

public interface ShopSaleRepository {

    void saveShopSale(ShopSale data);

    Optional<ShopSale> getSaleBySaleId(UUID saleId);

}
