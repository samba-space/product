package com.anysinsa.product.application;

import java.math.BigDecimal;
import java.util.List;

import com.anysinsa.product.application.dto.BrandResponseDTO;
import com.anysinsa.product.application.dto.CategoryResponseDTO;
import com.anysinsa.product.application.dto.ProductDetailResponse;
import com.anysinsa.product.client.BrandClient;
import com.anysinsa.product.client.CategoryClient;
import com.anysinsa.product.domain.Money;
import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;

import com.anysinsa.product.domain.Product;
import com.anysinsa.product.domain.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

@Observed
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandClient brandClient;
    private final CategoryClient categoryClient;
    public static Long id =1001L;

    public ProductService(ProductRepository productRepository, BrandClient brandClient, CategoryClient categoryClient) {
        this.productRepository = productRepository;
        this.brandClient = brandClient;
        this.categoryClient = categoryClient;
    }
    @Transactional
    public void saveProduct() {
        productRepository.save(new Product(id++, 1000L, new Money(new BigDecimal(10))));
    }

    @Transactional(readOnly = true)
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ProductDetailResponse findProductById(Long id) {
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        BrandResponseDTO findBrand = brandClient.findBrand(findProduct.getBrandId());
        CategoryResponseDTO findCategory = categoryClient.findCategory(findProduct.getCategoryId());

        return new ProductDetailResponse(findBrand.brandName(), findCategory.categoryName(), findProduct.getPrice().getValue());
    }
}
