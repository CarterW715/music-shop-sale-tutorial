package com.practice.musicsalesgood.repository;

import com.practice.musicsalesgood.repository.model.ShopReturn;
import com.practice.musicsalesgood.repository.model.ShopSale;

import java.util.Optional;
import java.util.UUID;

public interface SaleReturnRepository {

    void saveShopReturn(ShopReturn data);

    Optional<ShopReturn> getReturnBySaleId(UUID saleId);

}
