package com.cykj.mapper;

import com.cykj.bean.LogInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/11 18:26
 * @desc:
 */
@Repository
public interface LogInfoMapper {
    // 获取日志列表
    List<LogInfo> getLogInfoList();
    // 关键字查找
    List<LogInfo> getSearchLogInfo(@Param("userName") String userName);
    // 删除
    int deleteLogInfo(@Param("logId") int logId);
    // 新增日志
    int addLogInfo(LogInfo logInfo);
}
