package com.cykj.controller;

import com.cykj.bean.*;
import com.cykj.service.impl.MenuServiceImp;
import com.cykj.service.impl.RoleMenuServiceImp;
import com.cykj.service.impl.RoleServiceImp;
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
    @Autowired
    private MenuServiceImp menuService;
    @Autowired
    private RoleMenuServiceImp roleMenuService;
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
    @ResponseBody
    public List<UserInfo> getSearchUser(String userName){
        List<UserInfo> userList = userInfoService.getSearchUser(userName);
        return userList;
    }
    // 登录
    @GetMapping("/userInfoLogin")
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
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
    // 用户账号查重 userAccountExist
    @GetMapping("/userAccountExist")
    @ResponseBody
    public UserInfo userAccountExist(String userAccount){
        return userInfoService.userAccountExist(userAccount);
    }
    // 登录后修改用户最后登录时间，登录次数
    @GetMapping("/updateLoginDate")
    @ResponseBody
    public void updateLoginDate(int userId,int loginNumber,String loginDate){
        userInfoService.updateLoginDate(userId,loginNumber,loginDate);
    }
    // 动态获取菜单列表
    @GetMapping("/getMenu")
    @ResponseBody
    public  RoleMenu getMenu(int roleId){
        RoleMenu roleMenu = roleMenuService.getMenu(roleId);
        return roleMenu;
    }
    //=================================角色管理=============================//
    // 获取角色列表
    @GetMapping("/getRoleList")
    @ResponseBody
    public List<Role> getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        return roleList;
    }
    // 关键字获取角色
    @GetMapping("/getSearchRole")
    @ResponseBody
    public List<Role> getSearchRole(String roleName){
        List<Role> roles = roleService.getSearchRole(roleName);
        return roles;
    }
    // 添加新角色
    @GetMapping("/addRole")
    @ResponseBody
    public String addRole(String roleName,String roleDescribe,String roleDate,int[] addRoleMenus){
        // 先将角色添加 并查出添加后的roleId
        roleService.addRole(roleName,roleDescribe,roleDate);
        int roleId = roleService.getRoleId(roleName);
        // 增加角色对应的菜单
        for (int menuId : addRoleMenus) {
            roleMenuService.addRoleMenu(roleId,menuId);
        }
        return "添加成功";
    }
    // 删除角色 还要删除对应的菜单关系
    @GetMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(int[] roleIds){
        for (int roleId : roleIds) {
            roleService.deleteRole(roleId);
            roleMenuService.deleteRoleMenu(roleId);
        }
        return "删除成功";
    }
    // 修改角色 修改角色信息 然后删除对应角色的菜单，再添加修改后的菜单
    @GetMapping("/updateRole")
    @ResponseBody
    public String updateRole(int roleId,String roleName,String roleDescribe,int[] addRoleMenus){
        // 1.先修改角色信息
        roleService.updateRole(roleId,roleName,roleDescribe);
        // 2.删除角色对应的菜单关系
        roleMenuService.deleteRoleMenu(roleId);
        // 3.添加修改后的菜单关系
        for (int menuId : addRoleMenus) {
            roleMenuService.addRoleMenu(roleId,menuId);
        }
        return "修改成功";
    }
    // 查重角色名
    @GetMapping("/roleNameExist")
    @ResponseBody
    public Role roleNameExist(String roleName){
        return roleService.roleNameExist(roleName);
    }
    //=================================菜单管理=============================//
    // 添加新菜单
    @GetMapping("/addMenu")
    @ResponseBody
    public String addMenu(Menu menu){
        menuService.addMenu(menu);
        return "添加成功";
    }
    //修改用户信息
    @GetMapping("/updateMenu")
    @ResponseBody
    public String updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return "修改成功";
    }
    // 查菜单重名
    @GetMapping("/menuNameExist")
    @ResponseBody
    public Menu menuNameExist(String menuName){
        return menuService.menuNameExist(menuName);
    }
    // 删除菜单
    @GetMapping("/deleteMenuList")
    @ResponseBody
    public String deleteMenuList(int[] menuIds){
        for (int menuId : menuIds) {
            menuService.deleteMenuList(menuId);
        }
        return "删除成功";
    }

    @GetMapping("/getMenuData")
    @ResponseBody
    public List<Menu> getMenuData(){
        return menuService.getMenuData();
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
