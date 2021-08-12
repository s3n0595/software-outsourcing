package com.cykj.service;

import com.cykj.bean.LogInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/11 18:26
 * @desc:
 */
public interface LogInfoService {
    // 获取日志列表
    List<LogInfo> getLogInfoList();
    // 关键字查找
    List<LogInfo> getSearchLogInfo(String userName);
    // 删除
    int deleteLogInfo(int logId);
    // 新增日志
    int addLogInfo(LogInfo logInfo);
}
