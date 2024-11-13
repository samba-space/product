package com.anysinsa.product.application;

import java.util.List;
import java.util.Optional;

import com.anysinsa.product.application.dto.BrandResponseDTO;
import com.anysinsa.product.application.dto.CategoryResponseDTO;
import com.anysinsa.product.application.dto.ProductDetailResponse;
import com.anysinsa.product.client.BrandClient;
import com.anysinsa.product.client.CategoryClient;
import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;

import com.anysinsa.product.domain.Product;
import com.anysinsa.product.domain.ProductRepository;

@Observed
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandClient brandClient;
    private final CategoryClient categoryClient;

    public ProductService(ProductRepository productRepository, BrandClient brandClient, CategoryClient categoryClient) {
        this.productRepository = productRepository;
        this.brandClient = brandClient;
        this.categoryClient = categoryClient;
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public ProductDetailResponse findProductById(Long id) {
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        BrandResponseDTO findBrand = brandClient.findBrand(findProduct.getBrandId());
        CategoryResponseDTO findCategory = categoryClient.findCategory(findProduct.getCategoryId());

        return new ProductDetailResponse(findBrand.brandName(), findCategory.categoryName(), findProduct.getPrice().getValue());
    }
}
