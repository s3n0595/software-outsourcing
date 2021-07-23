package com.cykj.controller;

import com.cykj.bean.User;
import com.cykj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/user")
    public User user(){
        User user = new User();
        List<User> queryall = userServiceImpl.queryall();
        System.out.println(queryall.size());
        return queryall.get(0);

    }
}
