package com.anysinsa.product.application.dto;

import java.math.BigDecimal;

public record ProductDetailResponse(
        String brandName,
        String categoryName,
        BigDecimal price
) {}