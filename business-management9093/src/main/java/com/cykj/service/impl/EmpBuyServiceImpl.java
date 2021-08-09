package com.cykj.service.impl;

import com.cykj.bean.TradeWork;
import com.cykj.mapper.EmpBuyMapper;
import com.cykj.service.EmpBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/8 14:12
 */
@Service
public class EmpBuyServiceImpl implements EmpBuyService {
    @Autowired
    private EmpBuyMapper empBuyMapper;
    @Override
    public int selBalance(Integer employerId) {
        return empBuyMapper.selBalance(employerId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addTradeWork(TradeWork tradeWork, Integer balance) {
        try{
            int i=empBuyMapper.editBalance(tradeWork.getEmployerId(),balance);
            int j=empBuyMapper.addtradeWork(tradeWork);
            if(i>0 && j>0){
                return i;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public TradeWork oldtradeWork(TradeWork tradeWork) {
        return empBuyMapper.oldtradeWork(tradeWork);
    }

    @Override
    public List<Map<String, Object>> selbuyList(Integer employerId) {
        return empBuyMapper.selbuyList(employerId);
    }

    @Override
    public int editState(TradeWork tradeWork) {
        return empBuyMapper.editState(tradeWork);
    }
}
