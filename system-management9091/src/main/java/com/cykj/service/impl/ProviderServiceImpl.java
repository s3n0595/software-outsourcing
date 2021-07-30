package com.cykj.service.impl;

import com.cykj.bean.ProviderAccount;
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
    public Map<String, Object> queryProvider(int page, String searchInfo, int pageSize) {
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", providerMapper.queryProvider(page, searchInfo, pageSize));
        results.put("total", providerMapper.countProvider(searchInfo));
        return results;
    }

	@Override
	public boolean editProvider(ProviderAccount providerAccount, ProviderInfo providerInfo) {
		providerMapper.editProviderAccount(providerAccount);
		providerMapper.editProviderInfo(providerInfo);
		return true;
	}
}
