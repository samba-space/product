package com.anysinsa.product.client;

import com.anysinsa.product.application.dto.CategoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service", url = "http://anysinsa-alb-1164360136.ap-northeast-2.elb.amazonaws.com")
public interface CategoryClient {
    @GetMapping("/api/v1/categories/{categoryId}")
    CategoryResponseDTO findCategory(@PathVariable Long categoryId);
}
