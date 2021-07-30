package com.cykj.service;

import com.alipay.api.AlipayApiException;
import com.cykj.bean.AliPayBean;

public interface PayService {
    String aliPay(AliPayBean aliPayBean) throws AlipayApiException;
}
