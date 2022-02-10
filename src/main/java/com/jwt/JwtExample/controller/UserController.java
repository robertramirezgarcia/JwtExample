package com.jwt.JwtExample.controller;

import com.jwt.JwtExample.entity.User;
import com.jwt.JwtExample.entity.UserToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("user")
@Api(value = "users for jwt")
public class UserController {

    //Implementacion de seguridad
    @PostMapping("/login")
    public UserToken login(@RequestParam("user") String username, @RequestParam("password") String password){
        String token = getJWTToken(username);
        UserToken user = new UserToken();
        user.setUser(username);
        user.setToken(token);
        return user;
    }

    private String getJWTToken(String username){
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.
                commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts.builder().setId("robertJWT").setSubject(username)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //30 seg de duracion de la clave
                .setExpiration(new Date(System.currentTimeMillis()+ 30000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }

}
