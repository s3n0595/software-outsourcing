package com.cykj.controller;

import com.cykj.bean.EmployerAccount;
import com.cykj.service.impl.EmployerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guoquansen
 * @date 2021/7/26 1:32 下午
 */
@Controller
public class RegisterController {

    @Autowired
    private EmployerServiceImpl employerService;

    @PostMapping ("/employerReg")
    @ResponseBody
    public String addEmployer(EmployerAccount employerAccount){
        System.out.println("employerAccount = " + employerAccount);
        employerService.addUser(employerAccount);
        return "addEmployer";
    }

}
