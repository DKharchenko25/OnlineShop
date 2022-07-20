package com.dkharchenko.shop.repositories;

import com.dkharchenko.shop.entities.Cart;
import com.dkharchenko.shop.entities.Client;
import com.dkharchenko.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllCartByClient(Client client);

    void deleteAllByClient(Client client);

    void deleteByClientAndProduct(Client client, Product product);
}
