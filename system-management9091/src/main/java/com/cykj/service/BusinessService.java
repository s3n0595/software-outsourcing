package com.cykj.service;

import java.util.List;
import java.util.Map;

/**
 * @author guoquansen
 * @date 2021/8/7 11:21 上午
 */
public interface BusinessService {

    List<Map<String, Object>> queryAllDemand(int count);
}
