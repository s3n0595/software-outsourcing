package com.cykj.service;

import com.cykj.bean.TradeRetreat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/13 15:59
 * @desc:
 */
public interface TradeRetreatService {
    // 获取退款信息
    List<TradeRetreat> getTradeRetreatList();
    // 修改申请状态
    int updateTreatStatus(int tradeRetreatId,int retreatStatus);
}
