package com.anysinsa.product.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anysinsa.product.domain.Product;
import com.anysinsa.product.domain.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }
}
