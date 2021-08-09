package com.cykj.controller;

import com.cykj.bean.TradeWork;
import com.cykj.service.EmpBuyService;
import com.cykj.service.impl.EmpBuyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/8 14:03
 */
@RestController
@RequestMapping("buy")
@CrossOrigin
public class EmpBuyController {
    @Autowired
    private EmpBuyService empBuyService;
    @RequestMapping("buyBalance")//查询余额
    public int empBalance(Integer employerId){
        return empBuyService.selBalance(employerId);
    }

    @RequestMapping("works")//添加购买记录
    public int addtradeWork(TradeWork tradeWork,int worksPrice){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(date); // Formats a Date into a date/time string
        tradeWork.setTradeTime(dateTime);
        int i=empBuyService.addTradeWork(tradeWork,worksPrice);
        if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

    @RequestMapping("whetherworks")//查询是否重复购买
    public int oldtradework(TradeWork tradeWork){
        TradeWork tw=empBuyService.oldtradeWork(tradeWork);
        if(tw!=null){
            return 1;
        }else{
            return 0;
        }
    }

    @RequestMapping("buylist")//个人中心显示购买作品
    public List<Map<String, Object>> empbuyList(int employerId){
        List<Map<String, Object>> abcd=empBuyService.selbuyList(employerId);
        System.out.println(abcd.get(0).get("worksTitle").toString());
        return empBuyService.selbuyList(employerId);
    }

    @RequestMapping("confirm")//雇主确认收货
    public int edittradeState(TradeWork tradeWork){
        tradeWork.setTradeStatus(4);
        int i=empBuyService.editState(tradeWork);
        if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

}
