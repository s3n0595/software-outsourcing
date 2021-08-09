package com.cykj.service.impl;

import com.cykj.bean.Demand;
import com.cykj.bean.Works;
import com.cykj.mapper.BusinessMapper;
import com.cykj.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public List<Works> queryAllWorks(int page, int pageSize) {
        return businessMapper.queryAllWorks(page,pageSize);
    }

    @Override
    public int queryWorksTotal() {
        return businessMapper.queryWorksTotal();
    }
}
