package com.abinbev.product.api.service;

import com.abinbev.product.api.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByUsername(String username);

    void create(User user);

    void update(User user);

    void delete(String username);
}
