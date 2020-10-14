package com.abinbev.product.api.service;

import com.abinbev.product.api.model.Product;
import com.abinbev.product.api.model.Search;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> findAll();

    List<Product> findAllByOrderByNameAsc();

    Optional<Product> findById(Long id);

    List<Product> findByNameLike(String name);

    List<Product> search(Search search);

    void create(Product product);

    void update(Product product);

    void delete(Long id);
}
