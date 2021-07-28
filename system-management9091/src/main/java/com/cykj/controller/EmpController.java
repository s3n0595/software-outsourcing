package com.cykj.controller;


import com.cykj.bean.EmployerAccount;
import com.cykj.service.impl.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//雇主管理
@RestController
@RequestMapping("/employer")
public class EmpController {
    @Autowired
    private EmpServiceImpl empService;
    //获取雇主列表
    @GetMapping("/list")
    public Map<String,Object> showList(Integer pageNo, Integer pageSize, String searchInfo){
        List<EmployerAccount> emps= empService.emplist(pageNo,pageSize,searchInfo);
        int count=empService.countEmp(searchInfo);
        HashMap<String,Object> map=new HashMap();
        map.put("emps",emps);
        map.put("count",count);
        return map;
    }

    //修改雇主信息
    @RequestMapping("/edit")
    public String editEmp(EmployerAccount employerAccount){
        int i=empService.editEmp(employerAccount);
        if(i>0){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }

    //删除雇主
    @RequestMapping("/DeleteOne")
    public String delEmp(Integer employerId){
        int i=empService.delEmp(employerId);
        if(i>0){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    //查询雇主
    @RequestMapping("/selEmpList")
    public List<EmployerAccount> selEmpList(String searchInfo){
        List<EmployerAccount> emps=empService.selEmplist(searchInfo);
        return emps;
    }

    //批量删除
    @RequestMapping("/DeleteAll")
    public String delAllEmp(int[] delIds){
        for(int i=0;i<delIds.length;i++){
            empService.delEmp(delIds[i]);
        }
        return "删除成功";
    }
}
