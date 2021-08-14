package com.cykj.service;

import com.cykj.bean.CapitalFlow;
import com.cykj.bean.EmployerInfo;
import com.cykj.bean.TradeRetreat;
import com.cykj.bean.TradeWork;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/8 14:11
 */
public interface EmpBuyService {
    int selBalance(Integer employerId);
    int addTradeWork(TradeWork tradeWork, Integer balance, CapitalFlow capitalFlow);
    TradeWork oldtradeWork(TradeWork tradeWork);
    List<Map<String, Object>> selbuyList(Integer employerId);
    int editState(TradeWork tradeWork,CapitalFlow capitalFlow,int providerId);
    List<Map<String, Object>> selprobuyList(Integer providerId);
    int editproState(TradeWork tradeWork);
    EmployerInfo selEmpPwd(EmployerInfo employerInfo);
    int adTradeRetreat(TradeRetreat tradeRetreat,int tradeStatus);
    EmployerInfo findTransactionPwd(int employerId);
}
