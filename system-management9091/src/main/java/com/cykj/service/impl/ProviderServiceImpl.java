package com.cykj.service.impl;

import com.cykj.bean.ProviderInfo;
import com.cykj.mapper.ProviderMapper;
import com.cykj.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/7/27 10:09
 * @desc:
 */
@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
    private ProviderMapper providerMapper;

	@Override
    public Map<String, Object> queryAll() {
        List<ProviderInfo> providerList = providerMapper.queryAll();
//        System.out.println(providerList);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", providerList);
        results.put("total", providerList.size());
        return results;
    }
}
