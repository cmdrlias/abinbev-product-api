package com.abinbev.product.api.repository;

import com.abinbev.product.api.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
    List<Product> findAll();

    List<Product> findAllByOrderByNameAsc();

    Optional<Product> findById(Long id);

    List<Product> findByNameLike(String name);
}
