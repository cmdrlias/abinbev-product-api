package com.abinbev.product.api.dto;

public class AuthDTO {
    private String user;
    private String token;

    private final static String UNAUTHORIZED = "NOT AUTHORIZED";

    public AuthDTO() {}

    public AuthDTO(String user) {
        this.user =  user;
        this.token = UNAUTHORIZED;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
