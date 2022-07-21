package com.dkharchenko.shop.repositories;

import com.dkharchenko.shop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Query(value = "select * from orders where client_id = ?1", nativeQuery = true)
    Integer[] findAllOrderIdByClientId(Integer clientId);

    @Modifying
    @Query(value = "delete from orders where client_id = ?1", nativeQuery = true)
    void deleteByClientId(Integer clientId);
}
