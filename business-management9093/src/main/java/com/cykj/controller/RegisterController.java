package com.cykj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author guoquansen
 * @date 2021/7/26 1:32 下午
 */
@Controller
public class RegisterController {

    @RequestMapping("/employerReg")
    @ResponseBody
    public String addEmployer(){
        HashMap<String, Object> employerMap = new HashMap<>();


        return "addEmployer";
    }

}
