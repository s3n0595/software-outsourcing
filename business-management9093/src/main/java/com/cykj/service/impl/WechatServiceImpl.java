package com.cykj.service.impl;

import com.cykj.mapper.WechatMapper;
import com.cykj.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatServiceImpl implements WechatService {
    @Autowired
    private WechatMapper wechatMapper;

    @Override
    public int updatebal(int employerId, String balance) {
        return wechatMapper.updatebal(employerId, balance);
    }
}
