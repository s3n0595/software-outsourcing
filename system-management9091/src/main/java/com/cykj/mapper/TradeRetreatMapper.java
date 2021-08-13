package com.cykj.mapper;

import com.cykj.bean.TradeRetreat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/13 15:59
 * @desc:
 */
@Repository
public interface TradeRetreatMapper {
    // 获取退款信息
    List<TradeRetreat> getTradeRetreatList();
    // 修改申请状态
    int updateTreatStatus(@Param("tradeRetreatId") int tradeRetreatId,@Param("retreatStatus") int retreatStatus);
}
