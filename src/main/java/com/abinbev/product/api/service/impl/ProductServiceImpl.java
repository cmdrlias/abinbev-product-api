package com.abinbev.product.api.service.impl;

import com.abinbev.product.api.exception.ResponseException;
import com.abinbev.product.api.model.Product;
import com.abinbev.product.api.model.Search;
import com.abinbev.product.api.repository.ProductRepository;
import com.abinbev.product.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        try {
            return productRepository.findAll();
        } catch (ResponseException ex) {
            log.error("Error while finding product list = {}", ex.getStatus());
            throw ex;
        }
    }

    @Override
    public List<Product> findAllByOrderByNameAsc() {
        try {
            return productRepository.findAllByOrderByNameAsc();
        } catch (ResponseException ex) {
            log.error("Error while finding product list = {}", ex.getStatus());
            throw ex;
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            if (id != null) {
                return productRepository.findById(id);
            } else {
                log.warn("While searching for product, id can't be null.");
                throw new ResponseException("Id can't null.", HttpStatus.BAD_REQUEST);
            }
        } catch(ResponseException ex) {
            String errorMessage = "Error while finding product ";
            log.error(errorMessage + "with id = {}, {}", id, ex.getStatus());
            throw ex;
        }
    }

    @Override
    public List<Product> findByNameLike(String name) {
        try {
            if (name.isEmpty()) {
                log.warn("While searching for product, name can't be null.");
                throw new ResponseException("Name can't be null.", HttpStatus.BAD_REQUEST);
            } else {
                return productRepository.findByNameLike(name);
            }
        } catch(ResponseException ex) {
            String errorMessage = "Error while getting product ";
            log.error(errorMessage + "with name = {}, {}", name, ex.getStatus());
            throw ex;
        }
    }

    @Override
    public List<Product> search(Search search) {
        try {
            List<Product> list = productRepository.findAll();

            if (search != null) {
                List<Product> product = new ArrayList<>();

                if (!search.getByName().isEmpty()) {
                    product = list.stream().filter(p -> p.getName().contains(search.getByName())).collect(Collectors.toList());
                }

                if (search.getByPrice() > 0) {
                    product.clear();
                    product = list.stream().filter(p -> p.getPrice() == search.getByPrice()).collect(Collectors.toList());
                }

                if (search.getByBrand() != null) {
                    product.clear();
                    product = list.stream().filter(p -> p.getBrand() == search.getByBrand()).collect(Collectors.toList());
                }

                return product;

            } else return list;
        } catch(ResponseException ex) {
            log.error("Error while searching.");
            throw ex;
        }
    }

    @Override
    public void create(Product product) {
        try {
            if (product != null) {
                product.setId(getNext());
                productRepository.save(product);
            } else {
                log.warn("product can't be null");
                throw new ResponseException("Product can't be null", HttpStatus.BAD_REQUEST);
            }
        } catch(ResponseException ex) {
            log.error("Error while creating product, {}", ex.getStatus());
            throw ex;
        }
    }

    @Override
    public void update(Product product) {
        try {
            if (product != null) {
                productRepository.save(product);
            } else {
                log.warn("product can't be null");
                throw new ResponseException("Product can't be null", HttpStatus.BAD_REQUEST);
            }
        } catch(ResponseException ex) {
            log.error("Error while updating product, {}", ex.getStatus());
            throw ex;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            product.ifPresent(p -> productRepository.delete(p));
        } catch(ResponseException ex) {
            log.error("Error while deleting product with id = {}, {}", id, ex.getStatus());
            throw ex;
        }
    }

    private Long getNext() {
        List<Product> product = productRepository.findAll();
        return product.isEmpty() ? 1 : product.get(product.size() - 1).getId() + 1;
    }
}
