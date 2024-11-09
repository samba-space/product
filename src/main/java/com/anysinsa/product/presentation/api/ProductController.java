package com.anysinsa.product.presentation.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anysinsa.product.application.ProductService;
import com.anysinsa.product.domain.Product;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/api/v1/products")
    public ResponseEntity<List<Product>> findProducts() {
        return ResponseEntity.ok().body(productService.findProducts());
    }

    @GetMapping(path = "/health")
    public String health() {
        return "health-product";
    }
}
