package com.abinbev.product.api.controller;

import com.abinbev.product.api.exception.ResponseException;
import com.abinbev.product.api.model.User;
import com.abinbev.product.api.service.UserService;
import com.abinbev.product.api.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        log.info("New user {} created successfully, password = {}",
                user.getUsername(), user.getPassword());

        return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.update(user);
        log.info("User {} was updated.", user.getUsername());

        return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
    }

    @RequestMapping(value = {"/delete/username={username}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("username") String username) {
        try {
            userService.delete(username);
            return String.format("User %s deleted successfully.", username);
        } catch(ResponseException ex) {
            return String.format("Error while trying to delete user %s: %s", username, ex.getStatus());
        }
    }
}
