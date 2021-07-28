package com.cykj.mapper;


import com.cykj.bean.EmployerInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpInfoMapper {
    int editEmpInfo(EmployerInfo employerInfo);//修改雇主信息
}
