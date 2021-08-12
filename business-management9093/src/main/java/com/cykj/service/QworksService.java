package com.cykj.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/5 18:37
 */
public interface QworksService {
    List<Map<String, Object>> queryAllWork(int count, String  demandTypeName, int priceMin, int priceMax,String searchInfo);
    int countProWork(int providerId);
    Map<String,Object> workProInfo(int providerId);
}
