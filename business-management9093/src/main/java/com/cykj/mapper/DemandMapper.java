package com.cykj.mapper;

import com.cykj.bean.Demand;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/4 16:45
 * @desc:
 */
@Repository
public interface DemandMapper {
	List<Map<String, Object>> queryAllDemand(int count);
}
