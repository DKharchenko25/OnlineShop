package com.dkharchenko.shop.services;

import com.dkharchenko.shop.entities.Cart;
import com.dkharchenko.shop.entities.Client;
import com.dkharchenko.shop.entities.Product;
import com.dkharchenko.shop.exceptions.CartIsEmptyException;
import com.dkharchenko.shop.exceptions.ClientNotFoundException;
import com.dkharchenko.shop.exceptions.ProductNotFoundException;
import com.dkharchenko.shop.repositories.CartRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ClientService clientService;


    public void addToCart(Integer clientId, Integer productId)
            throws ClientNotFoundException, ProductNotFoundException {
        Client client = clientService.findById(clientId);
        Product product = productService.findById(productId);
        cartRepository.save(new Cart(client, product));
    }

    public List<Product> findAllProductsByClientId(Integer clientId) throws CartIsEmptyException {
        List<Cart> carts = cartRepository.findAllCartByClientId(clientId);
        List<Product> products = new ArrayList<>();
        for (Cart cart : carts) {
            products.add(cart.getProduct());
        }
        if (products.isEmpty()) {
            throw new CartIsEmptyException("Cart of client with ID #" + clientId + " is empty");
        }
        return products;
    }

    public void deleteAllByClientId(Integer clientId) {
        cartRepository.deleteAllByClientId(clientId);

    }

    public Integer[] deleteFromCart(Integer clientId, Integer productId) throws ClientNotFoundException, ProductNotFoundException {
        if (clientService.findById(clientId).getId() != clientId) {
            throw new ClientNotFoundException("Client with ID #" + clientId + " is not found");
        }
        if (productService.findById(productId).getId() != productId) {
            throw new ProductNotFoundException("Product with ID #" + clientId + " is not found");
        }
        cartRepository.deleteByClientIdAndProductId(clientId, productId);
        return new Integer[]{clientId, productId};
    }
}

