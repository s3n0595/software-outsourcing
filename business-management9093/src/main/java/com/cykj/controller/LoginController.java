package com.cykj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guoquansen
 * @date 2021/7/24 8:37 下午
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public String login(){
        System.err.println("----------->进来了");
        return "login";
    }
}
