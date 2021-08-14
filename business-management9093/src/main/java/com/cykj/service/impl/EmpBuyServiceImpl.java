package com.cykj.service.impl;

import com.cykj.bean.CapitalFlow;
import com.cykj.bean.EmployerInfo;
import com.cykj.bean.TradeRetreat;
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
    public int addTradeWork(TradeWork tradeWork, Integer balance, CapitalFlow capitalFlow) {
        try{
            int i=empBuyMapper.editBalance(tradeWork.getEmployerId(),balance);
            int j=empBuyMapper.addtradeWork(tradeWork);
            int k=empBuyMapper.addFlow(capitalFlow);
            if(i>0 && j>0 && k>0){
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int editState(TradeWork tradeWork,CapitalFlow capitalFlow,int providerId) {
        try{
            int i=empBuyMapper.editState(tradeWork);
            int j=empBuyMapper.addproFlow(capitalFlow);
            int k=empBuyMapper.editproBalance(providerId, (int) capitalFlow.getTradeCapital());
            if(i>0 && j>0 && k>0){
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
//        return empBuyMapper.editState(tradeWork);
    }

    @Override
    public List<Map<String, Object>> selprobuyList(Integer providerId) {
        return empBuyMapper.selprobuyList(providerId);
    }

    @Override
    public int editproState(TradeWork tradeWork) {
        return empBuyMapper.editproState(tradeWork);
    }

    @Override
    public EmployerInfo selEmpPwd(EmployerInfo employerInfo) {
        return empBuyMapper.selEmpPwd(employerInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int adTradeRetreat(TradeRetreat tradeRetreat, int tradeStatus) {
        try{
            int i=empBuyMapper.addtradeRetreat(tradeRetreat);
            int j=empBuyMapper.editRetreat(tradeRetreat.getTradeWorksId(),tradeStatus);
            if(i>0 && j>0){
                return 1;
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
    public EmployerInfo findTransactionPwd(int employerId) {
        return empBuyMapper.findTransactionPwd(employerId);
    }
}
