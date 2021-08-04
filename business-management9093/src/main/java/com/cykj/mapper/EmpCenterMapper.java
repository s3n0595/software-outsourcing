package com.cykj.mapper;


import com.cykj.bean.Demand;
import com.cykj.bean.DemandType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpCenterMapper {
    int addNeed(Demand demand);//添加雇主需求
    List<Demand> nedds(@Param("employerId") int id);//雇主过往需求列表
    List<DemandType> ckList();//获取需求类型表
    int editNeed(Demand demand);//修改雇主需求信息
    int delNeed(@Param("demandId") int did);
    int updatePwd(@Param("employerId") int employerId,@Param("employerPassword") String employerPassword,@Param("password") String password);
}
