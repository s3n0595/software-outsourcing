package com.cykj.mapper;

import com.cykj.bean.ProviderAccount;
import com.cykj.bean.ProviderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/7/27 10:09
 * @desc:
 */
@Repository
public interface ProviderMapper {
	List<Map<String, Object>> queryProvider(@Param("page") int page, @Param("searchInfo") String searchInfo, @Param("pageSize") int pageSize);
	int countProvider(String searchInfo);
	int editProviderAccount(ProviderAccount providerAccount);
	int editProviderInfo(ProviderInfo providerInfo);
}
