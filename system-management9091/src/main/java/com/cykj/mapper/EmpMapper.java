package com.cykj.mapper;


import com.cykj.bean.EmployerAccount;
import com.cykj.bean.EmployerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper {
    List<EmployerAccount> emplist(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("searchInfo")String searchInfo);//获取雇主列表
    int countEmp(@Param("searchInfo")String searchInfo);
    int editEmp(EmployerAccount employerAccount);//修改雇主信息
    int editEmpInfo(EmployerInfo employerInfo);
    int delEmp(@Param("employerId") Integer eId);//删除雇主
    int delEmpInfo(@Param("employerId") Integer eId);
    List<EmployerAccount> selEmplist(@Param("employerName") String name);//查询雇主
}
