package com.cykj.mapper;

import com.cykj.bean.ProviderAccount;
import com.cykj.bean.TradeRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/10 17:46
 * @desc:
 */
@Repository
public interface TradeRecordMapper {
    // 获取投标交易表
    List<TradeRecord> getTradeRecord();
    // 通过providerId查找服务商
    ProviderAccount getProvider(@Param("providerId") int providerId);
}
