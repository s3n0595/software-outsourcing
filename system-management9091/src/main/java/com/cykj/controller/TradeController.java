package com.cykj.controller;

import com.cykj.bean.ProviderAccount;
import com.cykj.bean.TradeRecord;
import com.cykj.bean.TradeWork;
import com.cykj.mapper.TradeRecordMapper;
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
    private TradeRecordMapper tradeRecordMapper;
    //===========================作品交易==============================//
    // 获取作品交易列表
    @GetMapping("/getTradeWordList")
    public List<TradeWork> getTradeWordList(){
        return tradeWorksService.getTradeWordList();
    }
    //===========================投标交易==============================//
    @GetMapping("/getTradeRecord")
    public List<TradeRecord> getTradeRecord(){
        return tradeRecordMapper.getTradeRecord();
    }
    // 通过providerId获取服务商
    @GetMapping("/getProvider")
    public ProviderAccount getProvider(int providerId){
        System.out.println(providerId);
        return tradeRecordMapper.getProvider(providerId);
    }
}
