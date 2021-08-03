package com.cykj.service.impl;

import com.cykj.bean.Sms;
import com.cykj.mapper.SmsMapper;
import com.cykj.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/3 9:28 下午
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsMapper smsMapper;

    @Override
    public List<Sms> querySms() {
        return smsMapper.querySms();
    }
}
