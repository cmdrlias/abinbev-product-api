package com.abinbev.product.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Auth {
    private String user;
    private String password;
    @JsonIgnore
    private String token;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
