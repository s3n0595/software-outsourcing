package com.cykj.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/5 18:37
 */
public interface QworksService {
    List<Map<String, Object>> queryAllWork(int count);
}
