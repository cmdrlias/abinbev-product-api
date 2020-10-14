package com.abinbev.product.api.controller;

import com.abinbev.product.api.dto.AuthDTO;
import com.abinbev.product.api.model.Auth;
import com.abinbev.product.api.model.User;
import com.abinbev.product.api.service.UserService;
import com.abinbev.product.api.service.impl.ProductServiceImpl;
import com.abinbev.product.api.utils.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SecurityController {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private UserService userService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @RequestMapping(value = {"auth"}, method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public AuthDTO login(@RequestBody Auth auth) {
        Optional<User> user = userService.findByUsername(auth.getUser());
        if(user.isPresent()) {
            if(passwordEncoder.matches(auth.getPassword(), user.get().getPassword())) {
                String token = getJWTToken(auth.getUser());
                auth.setToken(token);

                return ObjectMapperUtils.map(auth, AuthDTO.class);
            } else {
                log.info("Password does not match for existing user = {}.", auth.getUser());
                return new AuthDTO("Password doesn't match username");
            }
        } else {
            log.info("User {} does not exists.", auth.getUser());
            return new AuthDTO("User does not exists");
        }
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
