package io.github.aditid0205.smartinventory.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull
    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @Column(precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.valueOf(5.0);
    @NotNull
    @NotBlank(message = "Sku is mandatory")
    @Column(unique = true)
    private String sku;

    public Product(String name, String brand, BigDecimal price, String sku) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sku = sku;
    }

    public Product(String name, String brand, String price, String sku) {
        this.name = name;
        this.brand = brand;
        this.price = new BigDecimal(price);
        this.sku = sku;
    }

    public Product() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
