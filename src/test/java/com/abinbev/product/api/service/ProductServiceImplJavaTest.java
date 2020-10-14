package com.abinbev.product.api.service;

import com.abinbev.product.api.exception.ResponseException;
import com.abinbev.product.api.model.Product;
import com.abinbev.product.api.model.Search;
import com.abinbev.product.api.repository.ProductRepository;
import com.abinbev.product.api.service.impl.ProductServiceImpl;
import com.abinbev.product.api.utils.Brand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import static org.mockito.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImplJavaTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    @Spy
    ProductServiceImpl productService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        List<Product> response = new ArrayList<>();

        response.add(new Product());

        Mockito.when(productRepository.findAll()).thenReturn(response);
        List<Product> result = productService.findAll();

        Assert.assertEquals(productService.findAll(), result);
    }

    @Test(expected = ResponseException.class)
    public void findAllThrowsException() {
        Mockito.when(productRepository.findAll()).thenThrow(new ResponseException("Exception for findAll"));

        productService.findAll();
    }

    @Test
    public void findAllByOrderByNameAsc() {
        List<Product> response = new ArrayList<>();

        response.add(new Product());

        Mockito.when(productRepository.findAllByOrderByNameAsc()).thenReturn(response);
        List<Product> result = productService.findAllByOrderByNameAsc();

        Assert.assertEquals(productService.findAllByOrderByNameAsc(), result);
    }

    @Test(expected = ResponseException.class)
    public void findAllByOrderByNameAscThrowsException() {
        Mockito.when(productRepository.findAllByOrderByNameAsc()).thenThrow(new ResponseException("Exception for findAllByOrderByNameAsc"));

        productService.findAllByOrderByNameAsc();
    }

    @Test
    public void findById() {
        Optional<Product> response = Optional.of(new Product());
        Mockito.when(productRepository.findById((long) 1)).thenReturn(response);

        productService.findById((long) 1);
    }

    @Test(expected = ResponseException.class)
    public void findByIdNull() {
        Mockito.when(productRepository.findById(null)).thenThrow(new ResponseException("Exception for findById"));

        productService.findById(null);
    }

    @Test
    public void findByNameLike() {
        List<Product> response = new ArrayList<>();

        response.add(new Product());

        Mockito.when(productRepository.findByNameLike("test")).thenReturn(response);

        productService.findByNameLike("test");
    }

    @Test(expected = ResponseException.class)
    public void findByNameLikeNull() {
        Mockito.when(productRepository.findByNameLike("")).thenThrow(new ResponseException("Exception for findByNameLike"));

        productService.findByNameLike("");
    }

    @Test
    public void search() {
        Search search = new Search("Name", 12, Brand.BOHEMIA);

        List<Product> response = new ArrayList<>();

        response.add(new Product((long) 1, "Name", "Test", 12, Brand.BOHEMIA));

        Mockito.when(productRepository.findAll()).thenReturn(response);

        productService.search(search);
    }

    @Test
    public void searchReturnsFullList() {
        List<Product> response = new ArrayList<>();

        response.add(newProduct());

        Mockito.when(productRepository.findAll()).thenReturn(response);

        List<Product> result = productService.search(null);

        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseException.class)
    public void searchThrowsException() {
        List<Product> response = new ArrayList<>();

        response.add(new Product((long) 1, "Name", "Test", 12, Brand.BOHEMIA));

        //Mockito.when(productRepository.findAll()).thenReturn(response);

        Mockito.when(productRepository.findAll()).thenThrow(new ResponseException("Exception for search"));

        productService.search(null);
    }

    @Test
    public void create() {
        Product product = newProduct();

        Mockito.when(productRepository.save(product)).thenReturn(product);

        productService.create(product);

        Mockito.verify(productService, Mockito.atMostOnce()).create(product);
    }

    @Test(expected = ResponseException.class)
    public void createNull() {

        Mockito.when(productRepository.save(null)).thenReturn(null);

        productService.create(null);

        Mockito.verify(productService, Mockito.atMostOnce()).create(null);
    }

    @Test
    public void update() {
        Product product = newProduct();

        Mockito.when(productRepository.save(product)).thenReturn(product);

        productService.update(product);

        Mockito.verify(productService, Mockito.atMostOnce()).update(product);
    }

    @Test(expected = ResponseException.class)
    public void updateNull() {

        Mockito.when(productRepository.save(null)).thenReturn(null);

        productService.update(null);

        Mockito.verify(productService, Mockito.atMostOnce()).update(null);
    }

    @Test
    public void delete() {
        Optional<Product> response = Optional.of(new Product());
        Mockito.when(productRepository.findById((long) 1)).thenReturn(response);
        Mockito.doNothing().when(productRepository).delete(response.get());

        productService.delete((long) 1);

        Mockito.verify(productService, Mockito.atMostOnce()).delete((long) 1);
    }

    @Test(expected = ResponseException.class)
    public void deleteThrowsException() {
        Optional<Product> response = Optional.of(new Product());

        Mockito.when(productRepository.findById((long) 0)).thenThrow(new ResponseException("Delete exception"));
        Mockito.doNothing().when(productRepository).delete(response.get());

        productService.delete((long) 0);

        Mockito.verify(productService, Mockito.atMostOnce()).delete((long) 0);
    }

    public Product newProduct() {
        return new Product((long) 1, "Name", "Test", 12, Brand.BOHEMIA);
    }
}
