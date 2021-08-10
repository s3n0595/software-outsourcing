package com.cykj.controller;

import com.cykj.bean.TradeRecord;
import com.cykj.bean.TradeWork;
import com.cykj.service.impl.TradeWorksServiceImp;
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
public class TradeController {
    @Autowired
    private TradeWorksServiceImp tradeWorksService;
    //===========================作品交易==============================//
    // 获取作品交易列表
    @GetMapping("/getTradeWordList")
    public List<TradeWork> getTradeWordList(){
        return tradeWorksService.getTradeWordList();
    }
    //===========================投标交易==============================//
}
