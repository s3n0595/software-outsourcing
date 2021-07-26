package com.cykj.controller;

import com.cykj.bean.User;
import com.cykj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/user")
    @ResponseBody
    public User user(){
        List<User> queryall = userServiceImpl.queryall();
        System.out.println(queryall.size());
        return queryall.get(0);
    }

}
