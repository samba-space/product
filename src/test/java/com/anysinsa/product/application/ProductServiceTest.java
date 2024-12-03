package com.anysinsa.product.application;

import com.anysinsa.product.application.dto.BrandResponseDTO;
import com.anysinsa.product.application.dto.CategoryResponseDTO;
import com.anysinsa.product.application.dto.ProductDetailResponse;
import com.anysinsa.product.client.BrandClient;
import com.anysinsa.product.client.CategoryClient;
import com.anysinsa.product.domain.Money;
import com.anysinsa.product.domain.Product;
import com.anysinsa.product.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BrandClient brandClient;

    @Mock
    private CategoryClient categoryClient;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;
    private BrandResponseDTO testBrand;
    private CategoryResponseDTO testCategory;

    @BeforeEach
    void setUp() {
        testProduct = new Product(1L, 1L, new Money(new BigDecimal("10000")));
        testBrand = new BrandResponseDTO(1L, "테스트 브랜드");
        testCategory = new CategoryResponseDTO(1L, "테스트 카테고리");
    }

    @Test
    @DisplayName("전체 상품 목록을 조회한다")
    void findProducts() {
        // given
        List<Product> products = Arrays.asList(
                new Product(1L, 1L, new Money(new BigDecimal("10000"))),
                new Product(2L, 2L, new Money(new BigDecimal("20000")))
        );
        given(productRepository.findAll()).willReturn(products);

        // when
        List<Product> result = productService.findProducts();

        // then
        assertThat(result).hasSize(2);
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("상품 ID로 상세 정보를 조회한다")
    void findProductById() {
        // given
        Long productId = 1L;
        given(productRepository.findById(productId)).willReturn(Optional.of(testProduct));
        given(brandClient.findBrand(testProduct.getBrandId())).willReturn(testBrand);
        given(categoryClient.findCategory(testProduct.getCategoryId())).willReturn(testCategory);

        // when
        ProductDetailResponse response = productService.findProductById(productId);

        // then
        assertThat(response.brandName()).isEqualTo("테스트 브랜드-fail");
        assertThat(response.categoryName()).isEqualTo("테스트 카테고리");
        assertThat(response.price()).isEqualTo(new BigDecimal("10000"));
    }

    @Test
    @DisplayName("존재하지 않는 상품 ID로 조회시 예외가 발생한다")
    void findProductById_NotFound() {
        // given
        Long productId = 999L;
        given(productRepository.findById(productId)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> productService.findProductById(productId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상품을 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("상품 ID로 상세 정보를 조회한다 (읽기 전용)")
    void findDetailProductById() {
        // given
        Long productId = 1L;
        given(productRepository.findById(productId)).willReturn(Optional.of(testProduct));

        // when
        Product result = productService.findDetailProductById(productId);

        // then
        assertThat(result).isEqualTo(testProduct);
        assertThat(result.getPrice().getValue()).isEqualTo(new BigDecimal("10000"));
    }

    @Test
    @DisplayName("존재하지 않는 상품 ID로 상세 조회시 예외가 발생한다")
    void findDetailProductById_NotFound() {
        // given
        Long productId = 999L;
        given(productRepository.findById(productId)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> productService.findDetailProductById(productId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상품을 찾을 수 없습니다.");
    }
}