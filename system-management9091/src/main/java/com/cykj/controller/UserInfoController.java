package com.cykj.controller;

import com.cykj.bean.CommonResult;
import com.cykj.bean.Menu;
import com.cykj.bean.Role;
import com.cykj.bean.UserInfo;
import com.cykj.service.impl.MenuServiceImp;
import com.cykj.service.impl.RoleServiceImp;
import com.cykj.service.impl.UserInfoServiceImp;
import com.cykj.vo.UserInfoPwd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    private MenuServiceImp menuService;
    @Autowired
    private RoleServiceImp roleService;
    // 获取用户列表
    @GetMapping("/getUserList")
    @ResponseBody
    public List<UserInfo> getUserList(){
        List<UserInfo> userList = userInfoService.getUserList();
        return userList;
    }
    // 通过关键字获取用户列表
    @GetMapping("/getSearchUser")
    public List<UserInfo> getSearchUser(String userName){
        List<UserInfo> userList = userInfoService.getSearchUser(userName);
        return userList;
    }
    // 登录
    @GetMapping("/userInfoLogin")
    public UserInfo userInfoLogin(String userAccount,String userPassword){
        System.out.println(userAccount+":"+userPassword);
        return userInfoService.userInfoLogin(userAccount,userPassword);
    }
    // 删除用户
    @GetMapping("/deleteUserList")
    @ResponseBody
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
    // 获取菜单列表
    @GetMapping("/getMenuData")
    public List<Menu> getMenuData(){
        List<Menu> menuList = menuService.getMenuData();
        return menuList;
    }
    // 获取角色列表
    @GetMapping("/getRoleList")
    public List<Role> getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        return roleList;
    }
    // 关键字获取角色
    @GetMapping("/getSearchRole")
    public List<Role> getSearchRole(String roleName){
        List<Role> roles = roleService.getSearchRole(roleName);
        return roles;
    }
    // 添加新角色
    @GetMapping("/addRole")
    public String getRole(Role role){
        System.out.println(role);
        int i = roleService.addRole(role);
        if (1==i){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }
    // 删除角色
    @GetMapping("/deleteRole")
    public String deleteRole(int[] roleIds){
        for (int roleId : roleIds) {
            roleService.deleteRole(roleId);
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
