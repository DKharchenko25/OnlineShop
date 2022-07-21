package com.dkharchenko.shop.controllers;

import com.dkharchenko.shop.dtos.ProductDTO;
import com.dkharchenko.shop.entities.Product;
import com.dkharchenko.shop.exceptions.ProductNotFoundException;
import com.dkharchenko.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping(value = "/page/{id}")
    public Page<Product> getProductsPage(@PathVariable Integer id) {
        return productService.getProductsPage(id);
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Integer id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @PostMapping("/post")
    public ResponseEntity<Integer> postNewProduct(@RequestBody ProductDTO dto) {
        Integer id = productService.addProduct(dto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public ResponseEntity<Integer> deleteProductById(@RequestParam Integer id) throws ProductNotFoundException {
        productService.deleteProductById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @Transactional
    public ResponseEntity<Integer> updateProductById(@RequestParam Integer id,
                                                     @RequestBody ProductDTO dto) throws ProductNotFoundException {
        productService.updateProductById(id, dto);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

}
