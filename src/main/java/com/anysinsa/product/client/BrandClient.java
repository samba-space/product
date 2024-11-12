package com.anysinsa.product.client;

import com.anysinsa.product.application.dto.BrandResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brand-service", url = "http://anysinsa-alb-1420791660.ap-northeast-2.elb.amazonaws.com")
public interface BrandClient {
    @GetMapping("/api/brands/{brandId}")
    BrandResponseDTO findBrand(@PathVariable Long brandId);
}
