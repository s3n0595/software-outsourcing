package com.cykj.service;

import com.cykj.bean.Demand;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/4 16:44
 * @desc:
 */
public interface DemandService {
	List<Map<String, Object>> queryAllDemand(int count);
}
