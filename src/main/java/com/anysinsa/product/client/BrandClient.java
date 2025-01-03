package com.anysinsa.product.client;

import com.anysinsa.common.config.FeignConfig;
import com.anysinsa.product.application.dto.BrandResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brand-service", url = "http://anysinsa-alb-1164360136.ap-northeast-2.elb.amazonaws.com", configuration = FeignConfig.class)
public interface BrandClient {
    @GetMapping("/api/v1/brands/{brandId}")
    BrandResponseDTO findBrand(@PathVariable Long brandId);
}

