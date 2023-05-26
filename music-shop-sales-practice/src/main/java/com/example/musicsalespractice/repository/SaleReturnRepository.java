package com.example.musicsalespractice.repository;

import com.practice.musicsalesgood.repository.model.ShopReturn;

import java.util.Optional;
import java.util.UUID;

public interface SaleReturnRepository {

    void saveShopReturn(ShopReturn data);

    Optional<ShopReturn> getReturnBySaleId(UUID saleId);

}
