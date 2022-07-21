package com.dkharchenko.shop.controllers;

import com.dkharchenko.shop.entities.Order;
import com.dkharchenko.shop.exceptions.CartIsEmptyException;
import com.dkharchenko.shop.exceptions.ClientNotFoundException;
import com.dkharchenko.shop.exceptions.OrderNotFoundException;
import com.dkharchenko.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/all")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping(value = "/{clientId}")
    public List<Order> getOrderByClientId(@PathVariable Integer clientId) throws OrderNotFoundException {
        return orderService.findByClientId(clientId);
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Integer> createOrder(@RequestParam Integer clientId)
            throws ClientNotFoundException, CartIsEmptyException {
        orderService.createOrder(clientId);
        return new ResponseEntity<>(clientId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public ResponseEntity<Integer> deleteOrderById(@RequestParam Integer clientId) throws ClientNotFoundException {
        orderService.deleteOrder(clientId);
        return new ResponseEntity<>(clientId, HttpStatus.OK);
    }

}
