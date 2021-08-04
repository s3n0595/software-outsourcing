package com.cykj.service.impl;

import com.cykj.bean.Demand;
import com.cykj.mapper.DemandMapper;
import com.cykj.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/4 16:45
 * @desc:
 */
@Service
public class DemandServiceImpl implements DemandService {
	@Autowired
	private DemandMapper demandMapper;

	@Override
	public List<Map<String, Object>> queryAllDemand(int count) {
		return demandMapper.queryAllDemand(count);
	}
}
