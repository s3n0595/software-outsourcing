package com.cykj.mapper;

import com.cykj.bean.ProviderInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/7/27 10:09
 * @desc:
 */
@Repository
public interface ProviderMapper {
	List<ProviderInfo> queryAll();
}
