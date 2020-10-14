package com.abinbev.product.api.service.impl;

import com.abinbev.product.api.exception.ResponseException;
import com.abinbev.product.api.model.User;
import com.abinbev.product.api.repository.UserRepository;
import com.abinbev.product.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch(ResponseException ex) {
            log.error("Error while finding user = {}", ex.getStatus());
            throw ex;
        }
    }

    @Override
    public void create(User user) {
        try {
            if (user != null) {
                Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
                if (existingUser.isPresent()) {
                    log.warn("User {} already exists.", user.getUsername());
                    throw new ResponseException("User already exists.", HttpStatus.BAD_REQUEST);
                } else {
                    userRepository.save(user);
                }
            } else {
                log.warn("user can't be null");
                throw new ResponseException("User can't be null", HttpStatus.CONFLICT);
            }
        } catch(ResponseException ex) {
            log.error("Error while creating user, {}", ex.getStatus());
            throw ex;
        }
    }

    @Override
    public void update(User user) {
        try {
            if (user != null) {
                userRepository.save(user);
            } else {
                log.warn("User can't be null");
                throw new ResponseException("User can't be null", HttpStatus.BAD_REQUEST);
            }
        } catch(ResponseException ex) {
            log.error("Error while updating user, {}", ex.getStatus());
            throw ex;
        }
    }

    @Override
    public void delete(String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            user.ifPresent(u -> userRepository.delete(u));
        } catch(ResponseException ex) {
            log.error("Error while deleting user {}, {}", username, ex.getStatus());
            throw ex;
        }
    }
}
