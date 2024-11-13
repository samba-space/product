package com.anysinsa.product.client;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.anysinsa.product.application.dto.BrandResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@XRayEnabled
@FeignClient(name = "brand-service", url = "http://anysinsa-alb-1164360136.ap-northeast-2.elb.amazonaws.com")
public interface BrandClient {
    @GetMapping("/api/v1/brands/{brandId}")
    BrandResponseDTO findBrand(@PathVariable Long brandId);
}

