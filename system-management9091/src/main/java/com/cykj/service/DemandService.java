package com.cykj.service;

import com.cykj.bean.Demand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/9 10:16
 * @desc:
 */
public interface DemandService {
    // 获取需求列表
    List<Demand> getDemandList();
    // 关键字
    List<Demand> getSearchDemand(String employerName);
    // 通过审核状态查找
    List<Demand> getSearchState(@Param("demandStatus") int demandStatus);
    // 修改审核状态
    int updateDemandState(int demandId,int demandStatus);
    // 根据demandId删除用户
    int deleteDemandList(int demandId);
}
