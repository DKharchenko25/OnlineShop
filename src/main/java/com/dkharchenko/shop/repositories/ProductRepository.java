package com.dkharchenko.shop.repositories;

import com.dkharchenko.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Query(value = "update Product set name = :newName where id = :requiredId")
    void updateNameById(@Param("newName") String newName, @Param("requiredId") Integer requiredId);

    @Modifying
    @Query(value = "update Product set price = :newPrice where id = :requiredId")
    void updatePriceById(@Param("newPrice") int newPrice, @Param("requiredId") Integer requiredId);

    @Modifying
    @Query(value = "update Product set name = :newName, price = :newPrice where id = :requiredId")
    void updateProductById(@Param("newName") String newName,
                           @Param("newPrice") int newPrice,
                           @Param("requiredId") Integer requiredId);


}
