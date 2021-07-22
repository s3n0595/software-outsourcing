package com.cykj.controller;

import com.cykj.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public User user(){
        User user = new User();
        return user;

    }
}
