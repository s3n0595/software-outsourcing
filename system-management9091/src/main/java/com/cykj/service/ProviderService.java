package com.cykj.service;

import com.cykj.bean.ProviderAccount;
import com.cykj.bean.ProviderInfo;

import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/7/27 10:08
 * @desc:
 */
public interface ProviderService {
	Map<String, Object> queryProvider(int page, String searchInfo, int pageSize);
	boolean editProvider(ProviderAccount providerAccount, ProviderInfo providerInfo);
}
