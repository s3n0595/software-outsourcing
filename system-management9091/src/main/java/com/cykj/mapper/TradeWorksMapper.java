package com.cykj.mapper;

import com.cykj.bean.TradeWork;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/10 21:29
 * @desc:
 */
@Repository
public interface TradeWorksMapper {
    // 获取作品交易列表
    List<TradeWork> getTradeWordList();
    // 修改作品交易状态
    int updateTradeStatus(@Param("tradeWorksId")int tradeWorksId,@Param("tradeStatus")int tradeStatus);
}
