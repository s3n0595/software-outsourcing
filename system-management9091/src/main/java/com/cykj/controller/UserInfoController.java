package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.UserInfo;
import com.cykj.service.impl.UserInfoServiceImp;
import com.cykj.vo.UserInfoPwd;
import lombok.extern.slf4j.Slf4j;
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
@Controller
@RequestMapping("/userinfo")
@Slf4j
public class UserInfoController {
    @Autowired
    private UserInfoServiceImp userInfoService;

    // 获取用户列表
    @GetMapping("/getUserList")
    @ResponseBody
    public List<UserInfo> getUserList(){
        List<UserInfo> userList = userInfoService.getUserList();
        System.out.println("访问到了");
        System.out.println(userList);
        return userList;
    }

    //删除用户
    @GetMapping("/deleteUserList")
    @ResponseBody
    public String deleteUserList(int[] userIds){
        for (Integer userId : userIds) {
            userInfoService.deleteUserList(userId);
        }
        return "删除成功";
    }

    //修改密码
    @RequestMapping("/updatePwd")
    @ResponseBody
    public CommonResult updatePwd(UserInfoPwd userInfoPwd){
        log.info("***修改密码***");
        log.info("***用户id***"+userInfoPwd.getUserId());
        int i = userInfoService.updatePwd(userInfoPwd.getUserId(), userInfoPwd.getUserPassword(), userInfoPwd.getPassword());
        if (i > 0) {
            return new CommonResult(200,"密码修改成功",null);
        } else {
            return new CommonResult(400,"密码修改失败",null);
        }
    }

}
