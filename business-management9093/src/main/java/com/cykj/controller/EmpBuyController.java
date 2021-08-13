package com.cykj.controller;

import com.cykj.bean.CapitalFlow;
import com.cykj.bean.EmployerInfo;
import com.cykj.bean.TradeRetreat;
import com.cykj.bean.TradeWork;
import com.cykj.service.EmpBuyService;
import com.cykj.service.impl.EmpBuyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("buyPwd")//判断交易密码是否正确
    public EmployerInfo empPwd(EmployerInfo employerInfo){
        return empBuyService.selEmpPwd(employerInfo);
    }

    @RequestMapping("works")//添加购买记录
    public int addtradeWork(TradeWork tradeWork,int worksPrice,String phoneNumber,String tradeContent){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(date); // Formats a Date into a date/time string
        tradeWork.setTradeTime(dateTime);
        CapitalFlow capitalFlow=new CapitalFlow();
        capitalFlow.setPhoneNumber(phoneNumber);
        capitalFlow.setTradeType("开发宝");
        capitalFlow.setTradeContent(tradeContent);
        capitalFlow.setTradeTime(dateTime);
        capitalFlow.setTradeCapital(worksPrice);
        capitalFlow.setTradeState("ACQ.TRADE_HAS_SUCCESS");
        capitalFlow.setType("雇主");
        int i=empBuyService.addTradeWork(tradeWork,worksPrice,capitalFlow);
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

    @RequestMapping("returnBuy")//雇主申请退货
    public int buyRetreat(TradeRetreat tradeRetreat,@RequestParam("tradeStatus") int tradeStatus){
        int i=empBuyService.adTradeRetreat(tradeRetreat,tradeStatus);
        if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

    @RequestMapping("probuylist")//服务商个人中心交易作品
    public List<Map<String, Object>> probuyList(int providerId){
        return empBuyService.selprobuyList(providerId);
    }
    @RequestMapping("probuysteps")//服务商完成进度条
    public int proeditSteps(TradeWork tradeWork){
       int i=empBuyService.editproState(tradeWork);
       if(i>0){
           return 1;
       }else{
           return 0;
       }

    }
}
