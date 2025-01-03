package com.anysinsa.product.domain;

import jakarta.persistence.*;

import com.anysinsa.common.domain.BaseTimeEntity;

@Entity
@Table(name = "product")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "categoryId")
    private Long categoryId;

    @Embedded private Money price;

    protected Product() {}

    public Product(Long brandId, Long categoryId, Money price) {
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Money getPrice() {
        return price;
    }

    public void updateFrom(Product product) {
        this.brandId = product.getBrandId();
        this.categoryId = product.getCategoryId();
        this.price = product.getPrice();
    }
}
