package com.dkharchenko.shop.repositories;

import com.dkharchenko.shop.entities.Client;
import com.dkharchenko.shop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findOrderByClient(Client client);
    void deleteByClient(Client client);
}
