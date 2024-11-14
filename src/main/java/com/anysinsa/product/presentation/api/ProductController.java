package com.anysinsa.product.presentation.api;

import java.util.List;

import com.anysinsa.common.handler.GlobalExceptionHandler;
import com.anysinsa.product.application.dto.ProductDetailResponse;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anysinsa.product.application.ProductService;
import com.anysinsa.product.domain.Product;

@Observed
@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/api/v1/products")
    public ResponseEntity<List<Product>> findProducts() {
        logger.info("findProducts");
        return ResponseEntity.ok().body(productService.findProducts());
    }

    @GetMapping(path = "/api/v1/products/{id}")
    public ResponseEntity<ProductDetailResponse> findProductById(@PathVariable Long id) {
        logger.info("findProductById::{}", id);
        return ResponseEntity.ok().body(productService.findProductById(id));
    }

    @GetMapping(path = "/api/v1/products/health")
    public String health() {
        return "health-product3";
    }
}
