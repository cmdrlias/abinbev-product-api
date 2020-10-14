package com.abinbev.product.api.utils;

public enum Brand {
    WALS("WÄLS"),
    BRAHMA("BRAHMA"),
    SKOL("SKOL"),
    BUDWEISER("BUDWEISER"),
    BOHEMIA("BOHEMIA"),
    COLORADO("CERVEJARIA COLORADO"),
    CORONA("CORONA"),
    ORIGINAL("ANTARTICA ORIGINAL"),
    STELLA("STELLA ARTOIS"),
    GUARANA("GUARANÁ ANTÁRTICA"),
    DOBEM("DO BEM"),
    GATORADE("GATORADE"),
    FUSION("FUSION"),
    PEPSI("PEPSI");

    public String brandName;

    Brand(String brandName) {
        this.brandName = brandName;
    }
}
