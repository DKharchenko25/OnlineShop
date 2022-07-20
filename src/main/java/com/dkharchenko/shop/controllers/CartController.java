package com.dkharchenko.shop.controllers;

import com.dkharchenko.shop.entities.Product;
import com.dkharchenko.shop.exceptions.ClientNotFoundException;
import com.dkharchenko.shop.exceptions.ProductNotFoundException;
import com.dkharchenko.shop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(value = "/client-all")
    public List<Product> getAllProductsByClientId(@RequestParam Integer clientId) throws ClientNotFoundException {
        return cartService.findAllProductsByClientId(clientId);
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addToCart(@RequestParam Integer clientId, @RequestParam Integer productId)
            throws ClientNotFoundException, ProductNotFoundException {
        cartService.addToCart(clientId, productId);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public ResponseEntity<Integer> deleteFromCart(@RequestParam Integer clientId, @RequestParam Integer productId)
            throws ClientNotFoundException, ProductNotFoundException {
        cartService.deleteFromCart(clientId, productId);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

}
