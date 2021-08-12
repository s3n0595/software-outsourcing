package com.cykj.mapper;

import com.cykj.bean.TradeRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/11 21:30
 * @desc:
 */
@Repository
public interface TradeRecordMapper {
	int insertRecord(TradeRecord tradeRecord);
	Map<String, Object> queryTradeRecordById(int tradeRecordId);
	int updateRecord(@Param("tradeRecordId") int tradeRecordId, @Param("tradeStatus") int tradeStatus);
}
