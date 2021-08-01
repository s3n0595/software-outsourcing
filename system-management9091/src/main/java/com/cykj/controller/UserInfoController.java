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
        System.out.println("获取用户列表");
        System.out.println(userList);
        return userList;
    }
    // 获取用户列表
    @GetMapping("/getSearchUser")
    public List<UserInfo> getSearchUser(String userName){
        List<UserInfo> userList = userInfoService.getSearchUser(userName);
        System.out.println("关键字获取的列表");
        System.out.println(userList);
        return userList;
    }
    // 删除用户
    @GetMapping("/deleteUserList")
    public String deleteUserList(int[] userIds){
        for (Integer userId : userIds) {
            userInfoService.deleteUserList(userId);
        }
        return "删除成功";
    }
    // 添加用户
    @GetMapping("/addUserInfo")
    public String addUserInfo(UserInfo userInfo){
        System.out.println("添加用户");
        System.out.println(userInfo);
        int i = userInfoService.addUserInfo(userInfo);
        System.out.println(i);
        if (1 == i){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }
    //修改状态
    @GetMapping("/updateState")
    public String updateState(int userId,int state){
        int i = userInfoService.updateState(userId,state);
        if (1 == i){
            System.out.println("userId为:"+userId+"的用户,状态更改为了:"+state);
            return "修改成功";
        }else {
            System.out.println("userId为:"+userId+"的用户,状态修改失败");
            return "修改失败";
        }
    }
    //修改用户信息
    @GetMapping("/updateUserInfo")
    public String updateUserInfo(int userId,String userName,int roleId){
        int i = userInfoService.updateUserInfo(userId,userName,roleId);
        if (1 == i){
            System.out.println("userId为:"+userId+"的用户,信息更改成功");
            return "修改成功";
        }else {
            System.out.println("userId为:"+userId+"的用户,信息修改失败");
            return "修改失败";
        }
    }


}
