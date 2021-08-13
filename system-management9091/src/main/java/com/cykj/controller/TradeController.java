package com.cykj.controller;

import com.cykj.bean.*;
import com.cykj.mapper.TradeRecordMapper;
import com.cykj.service.impl.ProviderInfoServiceImp;
import com.cykj.service.impl.TradeRecordServiceImp;
import com.cykj.service.impl.TradeRetreatServiceImp;
import com.cykj.service.impl.TradeWorksServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/10 17:36
 * @desc: 订单管理
 */
@RestController
@RequestMapping("/tradeManage")
@Slf4j
public class TradeController {
    @Autowired
    private TradeWorksServiceImp tradeWorksService;
    @Autowired
    private TradeRecordServiceImp tradeRecordService;
    @Autowired
    private TradeRetreatServiceImp tradeRetreatService;
    @Autowired
    private ProviderInfoServiceImp providerInfoService;
    //===========================作品交易==============================//
    // 获取作品交易列表
    @GetMapping("/getTradeWordList")
    public List<TradeWork> getTradeWordList(){
        return tradeWorksService.getTradeWordList();
    }
    //===========================投标交易==============================//
    @GetMapping("/getTradeRecord")
    public List<TradeRecord> getTradeRecord(){
        return tradeRecordService.getTradeRecord();
    }
    // 通过providerId获取服务商
    @GetMapping("/getProvider")
    public ProviderAccount getProvider(int providerId){
        System.out.println(providerId);
        return tradeRecordService.getProvider(providerId);
    }
    //===========================作品退款==============================//
    // 获取作品退款列表
    @GetMapping("/getTradeRetreatList")
    public List<TradeRetreat> getTradeRetreatList(){
        return tradeRetreatService.getTradeRetreatList();
    }
    // 修改申请状态updateTreatStatus
    @GetMapping("/updateTreatStatus")
    public String updateTreatStatus(int tradeRetreatId,int retreatStatus,int providerId,int employerId,int balance,String creditTime,String PPhoneNumber,String EPhoneNumber){
        // 判断申述情况 2：申述成功，卖家退款并且扣信誉，买家余额增加
        if (retreatStatus == 2){
            // 买家扣信誉 扣除余额
            providerInfoService.updateCredit(providerId,10,balance);
            // 退款给买家
            providerInfoService.updateEBalance(employerId,balance);
            // 添加流水
            providerInfoService.addCapitalFlow("开发宝",PPhoneNumber,"商品与描述不符，退款",balance,creditTime,"ACQ.TRADE_HAS_SUCCESS","服务商");
            providerInfoService.addCapitalFlow("开发宝",EPhoneNumber,"申述成功，退还",balance,creditTime,"ACQ.TRADE_HAS_SUCCESS","雇主");
            // 添加信誉记录
            providerInfoService.addCreditDetails(providerId,"商品与描述不符",creditTime,"pro","-10");
        }
       tradeRetreatService.updateTreatStatus(tradeRetreatId,retreatStatus);
       return "修改成功";
    }
}
