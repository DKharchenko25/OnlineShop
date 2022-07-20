package com.dkharchenko.shop.services;

import com.dkharchenko.shop.dtos.ProductDTO;
import com.dkharchenko.shop.entities.Product;
import com.dkharchenko.shop.exceptions.ProductNotFoundException;
import com.dkharchenko.shop.repositories.ProductRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> getProductsPage(Integer pageNumber) {
        Pageable pages = PageRequest.of(pageNumber, 5);
        return productRepository.findAll(pages);
    }

    public Product findById(Integer id) throws ProductNotFoundException {
        Product product;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            throw new ProductNotFoundException("Product with ID #" + id + " is not found");
        }
        return product;
    }

    public Integer addProduct(ProductDTO dto) {
       return productRepository.save(new Product(dto.getName(), dto.getPrice())).getId();
    }

    public Integer deleteProductById(Integer id) throws ProductNotFoundException {
        Product product = findById(id);
        productRepository.deleteById(product.getId());
        return product.getId();
    }

    public Integer updateProductById(Integer id, ProductDTO dto) throws ProductNotFoundException {
        Product product = findById(id);
        productRepository.updateProductById(dto.getName(), dto.getPrice(), product.getId());
        return product.getId();
    }

    public Integer updatePriceById(Integer id, ProductDTO dto) throws ProductNotFoundException {
        Product product = findById(id);
        productRepository.updatePriceById(dto.getPrice(), product.getId());
        return product.getId();
    }

    public Integer updateNameById(Integer id, ProductDTO dto) throws ProductNotFoundException {
        Product product = findById(id);
        productRepository.updateNameById(dto.getName(), product.getId());
        return product.getId();
    }


}
