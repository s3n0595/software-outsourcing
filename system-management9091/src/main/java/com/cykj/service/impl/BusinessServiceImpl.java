package com.cykj.service.impl;

import com.cykj.mapper.BusinessMapper;
import com.cykj.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Map<String, Object>> queryAllDemand(int count) {
        return businessMapper.queryAllDemand(count);
    }
}
