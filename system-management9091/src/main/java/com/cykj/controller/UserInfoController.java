package com.cykj.controller;

import com.cykj.bean.UserInfo;
import com.cykj.service.impl.UserInfoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/7/28 20:36
 * @desc:
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoServiceImp userInfoService;

    // 获取用户列表
    @GetMapping("/getUserList")
    public List<UserInfo> getUserList(){
        List<UserInfo> userList = userInfoService.getUserList();
        System.out.println("访问到了");
        System.out.println(userList);
        return userList;
    }

    //删除用户
    @GetMapping("/deleteUserList")
    public String deleteUserList(int[] userIds){
        for (Integer userId : userIds) {
            userInfoService.deleteUserList(userId);
        }
        return "删除成功";
    }

}
