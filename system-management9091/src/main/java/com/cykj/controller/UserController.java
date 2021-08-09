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


    public static void main(String[] args) {
        String a = "这是123ABCd";
        int b = 0 ;
        int c = 0;
        int d = 0;
        for(int i = 0; i< a.length() ;i++){
            // 取到a中的每个char
            char e = a.charAt(i);
            // 比较 ASCll码，来判断
            if((e >= 'a' && e <= 'z')||(e >= 'A' && e <= 'Z')){
                b++;
            }else if(e >= '0' && e <= '9'){
                c++;
            }else{
                d++;
            }
        }
        System.out.println("中文个数" + d);
        System.out.println("英文个数" + b);
        System.out.println("数字个数" + c);
    }

}
