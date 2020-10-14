package com.abinbev.product.api.controller;

import com.abinbev.product.api.dto.ProductDTO;
import com.abinbev.product.api.exception.ResponseException;
import com.abinbev.product.api.model.Product;
import com.abinbev.product.api.model.Search;
import com.abinbev.product.api.service.ProductService;
import com.abinbev.product.api.service.impl.ProductServiceImpl;
import com.abinbev.product.api.utils.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/all"}, method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<ProductDTO> getAll() {
        try {
            return ObjectMapperUtils.mapAll(productService.findAll(), ProductDTO.class);
        } catch (ResponseException ex) {
            log.error("Error while getting product list = {}", ex.getStatus());
            throw ex;
        }
    }

    @RequestMapping(value = {"/all/order/name"}, method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<ProductDTO> getAllOrderByName() {
        try {
            return ObjectMapperUtils.mapAll(productService.findAllByOrderByNameAsc(), ProductDTO.class);
        } catch (ResponseException ex) {
            log.error("Error while getting product list = {}", ex.getStatus());
            throw ex;
        }
    }

    @RequestMapping(value = {"/get/{id}"}, method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Optional<ProductDTO> getById(@PathVariable("id") Long id) {
        return Optional.of(ObjectMapperUtils.map(productService.findById(id), ProductDTO.class));
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST, produces="application/json", consumes="application/json")
    @ResponseBody
    public List<ProductDTO> getByName(@RequestBody Search search) {
        List<Product> result = productService.search(search);

        if (!result.isEmpty()) {
            int size = result.size();
            String message = size > 1 ? "Found {} registries for this search." : "Found {} registry for this search.";
            log.info(message, size);
        } else
            log.info("No results found for this search.");
        return ObjectMapperUtils.mapAll(result, ProductDTO.class);
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Product product) {
        productService.create(product);
        log.info("New product created with id = {}, name = {}, brand = {}",
                product.getId(), product.getName(), product.getBrand());

        return new ResponseEntity<>("Product created successfully!", HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Product product) {
        productService.update(product);
        log.info("Product with id = {} was updated.", product.getId());

        return new ResponseEntity<>("Product updated successfully!", HttpStatus.OK);
    }

    @RequestMapping(value = {"/delete/id={id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        log.info("Product with id = {} was deleted.", id);
        return  String.format("%s - Product deleted successfully.", HttpStatus.OK.toString());
    }
}
