package com.dkharchenko.shop.repositories;

import com.dkharchenko.shop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Modifying
    @Query(value = "select * from cart where client_id = ?1", nativeQuery = true)
    List<Cart> findAllCartByClientId(Integer clientId);

    @Modifying
    @Query(value = "select * from cart where product_id = ?1", nativeQuery = true)
    List<Cart> findAllCartByProductId(Integer productId);

    @Modifying
    @Query(value = "delete from cart where client_id = ?1", nativeQuery = true)
    void deleteAllByClientId(Integer clientId);

    @Modifying
    @Query(value = "delete from cart where (client_id = ?1 and product_id = ?2)", nativeQuery = true)
    void deleteByClientIdAndProductId(Integer clientId, Integer productId);
}
