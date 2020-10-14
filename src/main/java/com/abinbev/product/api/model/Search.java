package com.abinbev.product.api.model;

import com.abinbev.product.api.utils.Brand;

public class Search {
    private String byName;
    private double byPrice;
    private Brand byBrand;

    public Search() { }

    public Search(String byName, double byPrice, Brand byBrand) {
        this.byName = byName;
        this.byPrice = byPrice;
        this.byBrand = byBrand;
    }

    public String getByName() {
        return byName;
    }

    public void setByName(String byName) {
        this.byName = byName;
    }

    public double getByPrice() {
        return byPrice;
    }

    public void setByPrice(double byPrice) {
        this.byPrice = byPrice;
    }

    public Brand getByBrand() {
        return byBrand;
    }

    public void setByBrand(Brand byBrand) {
        this.byBrand = byBrand;
    }
}
