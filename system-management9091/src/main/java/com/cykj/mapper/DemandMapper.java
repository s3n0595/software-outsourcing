package com.cykj.mapper;

import com.cykj.bean.Demand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/9 10:14
 * @desc:
 */
@Repository
public interface DemandMapper {
    // 获取需求列表
    List<Demand> getDemandList();
    // 关键字
    List<Demand> getSearchDemand(@Param("employerName") String employerName);
    // 通过审核状态查找
    List<Demand> getSearchState(@Param("demandStatus") int demandStatus);
    // 修改审核状态
    int updateDemandState(@Param("demandId") int demandId,@Param("demandStatus") int demandStatus);
    // 根据demandId删除用户
    int deleteDemandList(@Param("demandId") int demandId);
}
