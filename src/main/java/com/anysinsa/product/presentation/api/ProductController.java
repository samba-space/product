package com.anysinsa.product.presentation.api;

import java.util.List;

import com.anysinsa.product.application.dto.ProductDetailResponse;
import io.micrometer.observation.annotation.Observed;
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

    @Observed
    @GetMapping(path = "/api/v1/products")
    public ResponseEntity<List<Product>> findProducts() {
        return ResponseEntity.ok().body(productService.findProducts());
    }
    
    @Observed
    @GetMapping(path = "/api/v1/products/{id}")
    public ResponseEntity<ProductDetailResponse> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findProductById(id));
    }

    @GetMapping(path = "/api/v1/products/health")
    public String health() {
        return "health-product3";
    }
}
