package com.cykj.service.impl;

import com.cykj.bean.LogInfo;
import com.cykj.mapper.LogInfoMapper;
import com.cykj.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/11 18:27
 * @desc:
 */
@Service
public class LogInfoServiceImp implements LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public List<LogInfo> getLogInfoList() {
        return logInfoMapper.getLogInfoList();
    }

    @Override
    public List<LogInfo> getSearchLogInfo(String userName) {
        return logInfoMapper.getSearchLogInfo(userName);
    }

    @Override
    public int deleteLogInfo(int logId) {
        return logInfoMapper.deleteLogInfo(logId);
    }

    @Override
    public int addLogInfo(LogInfo logInfo) {
        return logInfoMapper.addLogInfo(logInfo);
    }
}
