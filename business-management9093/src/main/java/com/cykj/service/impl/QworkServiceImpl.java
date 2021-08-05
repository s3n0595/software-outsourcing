package com.cykj.service.impl;

import com.cykj.mapper.QworksMapper;
import com.cykj.service.QworksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/5 18:38
 */
@Service
public class QworkServiceImpl implements QworksService {
    @Autowired
    private QworksMapper qworksMapper;
    @Override
    public List<Map<String, Object>> queryAllWork(int count) {
        return qworksMapper.queryAllWork(count);
    }
}
