package com.abinbev.product.api.dto;

import com.abinbev.product.api.utils.Brand;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String brand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand.brandName;
    }
}
