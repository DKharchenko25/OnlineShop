package com.dkharchenko.shop.services;


import com.dkharchenko.shop.entities.Cart;
import com.dkharchenko.shop.entities.Client;
import com.dkharchenko.shop.entities.Order;
import com.dkharchenko.shop.entities.Product;
import com.dkharchenko.shop.exceptions.CartIsEmptyException;
import com.dkharchenko.shop.exceptions.ClientNotFoundException;
import com.dkharchenko.shop.exceptions.OrderNotFoundException;
import com.dkharchenko.shop.exceptions.ProductNotFoundException;
import com.dkharchenko.shop.repositories.OrderRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final CartService cartService;

    public Integer createOrder(Integer clientId) throws ClientNotFoundException, CartIsEmptyException {
        Client client = clientService.findById(clientId);
        List<Product> allProductsByClientId = cartService.findAllProductsByClientId(clientId);
        int totalPrice = 0;
        for (Product product : allProductsByClientId) {
            totalPrice += product.getPrice();
        }
        if (totalPrice == 0) {
            throw new CartIsEmptyException("Cart of client ID #" + client.getId() + " is empty");
        }
        cartService.deleteAllByClientId(client.getId());
        return orderRepository.save(new Order(client, totalPrice)).getId();

    }

    public Integer deleteOrder(Integer clientId) throws ClientNotFoundException {
        Client client = clientService.findById(clientId);
        orderRepository.deleteByClient(client);
        return client.getId();
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findByClientId(Integer clientId) throws OrderNotFoundException, ClientNotFoundException {
        Order order;
        Client client = clientService.findById(clientId);
        Optional<Order> optionalOrder = orderRepository.findOrderByClient(client);
        if(optionalOrder.isPresent()) {
            order = optionalOrder.get();
        } else {
            throw new OrderNotFoundException("Order for client with ID #" + clientId + " is not found");
        }
        return order;
    }
}
