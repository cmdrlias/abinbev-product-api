package com.abinbev.product.api.service;

import com.abinbev.product.api.exception.ResponseException;
import com.abinbev.product.api.model.Product;
import com.abinbev.product.api.model.User;
import com.abinbev.product.api.repository.UserRepository;
import com.abinbev.product.api.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Optional;

public class UserServiceImplJavaTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    @Spy
    UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByUsername() {
        Optional<User> response = Optional.of(new User());
        Mockito.when(userRepository.findByUsername("Name")).thenReturn(response);

        userService.findByUsername("Name");
    }

    @Test(expected = ResponseException.class)
    public void findByIdNull() {
        Mockito.when(userRepository.findByUsername(null)).thenThrow(new ResponseException("Exception for findByUsername"));

        userService.findByUsername(null);
    }

    @Test
    public void create() {
        User user = newUser();

        Mockito.when(userRepository.save(user)).thenReturn(user);

        userService.create(user);

        Mockito.verify(userService, Mockito.atMostOnce()).create(user);
    }

    @Test(expected = ResponseException.class)
    public void createNull() {

        Mockito.when(userRepository.save(null)).thenReturn(null);

        userService.create(null);

        Mockito.verify(userService, Mockito.atMostOnce()).create(null);
    }

    @Test(expected = ResponseException.class)
    public void createExistingUser() {

        User user = newUser();

        Optional<User> response = Optional.of(user);
        Mockito.when(userRepository.findByUsername("username")).thenReturn(response);

        userService.create(user);

        Mockito.verify(userService, Mockito.atMostOnce()).create(user);
    }

    @Test
    public void update() {
        User user = newUser();

        Mockito.when(userRepository.save(user)).thenReturn(user);

        userService.update(user);

        Mockito.verify(userService, Mockito.atMostOnce()).update(user);
    }

    @Test(expected = ResponseException.class)
    public void updateNull() {

        Mockito.when(userRepository.save(null)).thenReturn(null);

        userService.update(null);

        Mockito.verify(userService, Mockito.atMostOnce()).update(null);
    }

    @Test
    public void delete() {
        Optional<User> response = Optional.of(new User());
        Mockito.when(userRepository.findByUsername("username")).thenReturn(response);
        Mockito.doNothing().when(userRepository).delete(response.get());

        userService.delete("username");

        Mockito.verify(userService, Mockito.atMostOnce()).delete("username");
    }

    @Test(expected = ResponseException.class)
    public void deleteThrowsException() {
        Optional<User> response = Optional.of(new User());

        Mockito.when(userRepository.findByUsername("")).thenThrow(new ResponseException("Delete exception"));
        Mockito.doNothing().when(userRepository).delete(response.get());

        userService.delete("");

        Mockito.verify(userService, Mockito.atMostOnce()).delete("");
    }

    public User newUser() {
        return new User("username", "password");
    }
}
