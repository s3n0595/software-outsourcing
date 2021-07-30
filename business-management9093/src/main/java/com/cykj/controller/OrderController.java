package com.cykj.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConfig;
import com.alipay.api.internal.util.AlipaySignature;
import com.cykj.bean.AliPayBean;
import com.cykj.service.impl.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping( "/app")
public class OrderController {
    @Autowired
    private PayServiceImpl payService;

    @RequestMapping("/index")
    public String abc(){
            return "index.html";
    }

    @RequestMapping( "/aliPay")
    public String alipay(String outTradeNo, String subject, String totalAmount, String body) throws AlipayApiException {
        AliPayBean alipayBean = new AliPayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        System.out.println("!"+payService.aliPay(alipayBean));
        return payService.aliPay(alipayBean);
    }

    @RequestMapping( "/success")
    public void success(HttpServletRequest request)throws Exception{
        String out_trade_no= request.getParameter("out_trade_no");
        String total_amount= request.getParameter("total_amount");
        String timestamp=new String(request.getParameter("timestamp").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("订单号"+out_trade_no);
        System.out.println("金额"+total_amount);
        System.out.println("支付时间"+timestamp);
        System.out.println("支付成功！");

    }

    @RequestMapping( "/successa")
    public void success2(){
        System.out.println("支付成功异步！");
    }
}
