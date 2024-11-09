package com.anysinsa.product.application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;

import com.anysinsa.product.domain.Money;
import com.anysinsa.product.domain.Product;

public record ProductUpdateRequest(
        @Min(1L) Long brandId, @Min(1L) Long categoryId, @Min(1L) BigDecimal price) {
    public Product toProduct() {
        return new Product(brandId, categoryId, new Money(price));
    }
}
