package com.jwt.JwtExample.controller;

import com.jwt.JwtExample.entity.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("list-user")
@Api(value = "list users for jwt")
public class ListUserController {

    @GetMapping(path = "list")
    public List<User> getUsers(){
        return getAllUsers().collect(Collectors.toList());
    }

    private Stream<User> getAllUsers(){
        List<User> userList = new ArrayList<User>();
        try{
            userList.add(new User(1, "Juan"));
            Thread.sleep(500);
            userList.add(new User(1, "Pedro"));
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println("Error: " + e);
        }
        return userList.stream();
    }
}
