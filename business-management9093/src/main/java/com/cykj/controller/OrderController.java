package com.cykj.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConfig;
import com.alipay.api.internal.util.AlipaySignature;
import com.cykj.bean.AliPayBean;
import com.cykj.bean.CapitalFlow;
import com.cykj.service.EmpCenterService;
import com.cykj.service.EmployerService;
import com.cykj.service.impl.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping( "app")
@CrossOrigin
public class OrderController {
    @Autowired
    private PayServiceImpl payService;
    @Autowired
    private EmpCenterService empCenterService;
    @RequestMapping("/index")
    public String abc(){
            return "index.html";
    }

    @RequestMapping( "aliPay")
    public String alipay(AliPayBean aliPayBean) throws AlipayApiException {
        CapitalFlow capitalFlow = new CapitalFlow();
        capitalFlow.setTradeType("支付宝");
        capitalFlow.setTradeContent(aliPayBean.getSubject());
        capitalFlow.setTradeCapital(Double.parseDouble(aliPayBean.getTotal_amount()));
        capitalFlow.setTradeNo(aliPayBean.getOut_trade_no());
        capitalFlow.setPhoneNumber(aliPayBean.getPhoneNumber());
        capitalFlow.setTradeState("ACQ.TRADE_STATUS_ERROR");
        capitalFlow.setType(aliPayBean.getType());
        empCenterService.addFlow(capitalFlow);
        return payService.aliPay(aliPayBean);
    }

    @RequestMapping( "/success")
    public void success(HttpServletRequest request)throws Exception{
        String timestamp=new String(request.getParameter("timestamp").getBytes("ISO-8859-1"), "UTF-8");
        String out_trade_no=request.getParameter("out_trade_no");
        String total_amount=request.getParameter("total_amount");
        CapitalFlow cap= empCenterService.seleEmpphone(out_trade_no);
        if(cap.getType().equals("雇主")){
            int employerId=empCenterService.seleEmpId(cap.getPhoneNumber());
            empCenterService.editEmpBalance(employerId, Double.parseDouble(total_amount));
        }
        CapitalFlow capitalFlow=new CapitalFlow();
        capitalFlow.setTradeNo(out_trade_no);
        capitalFlow.setTradeTime(timestamp);
        capitalFlow.setTradeState("ACQ.TRADE_HAS_SUCCESS");
        empCenterService.updFlow(capitalFlow);
        System.out.println("支付成功！");
    }

    @RequestMapping( "/successa")
    public void success2(){
        System.out.println("支付成功异步！");
    }
}
