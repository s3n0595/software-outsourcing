package com.cykj.service.impl;

import com.alipay.api.AlipayApiException;
import com.cykj.bean.AliPayBean;
import com.cykj.component.Alipay;
import com.cykj.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private Alipay alipay;
    @Override
    public String aliPay(AliPayBean aliPayBean) throws AlipayApiException {
        return alipay.pay(aliPayBean);
    }
}
