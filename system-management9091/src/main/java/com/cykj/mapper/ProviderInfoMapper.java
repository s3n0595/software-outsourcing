package com.cykj.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @author: Sapphier Star
 * @date: 2021/8/13 18:49
 * @desc:
 */
@Repository
public interface ProviderInfoMapper {
    // 修改卖家信誉分跟余额
    int updateCredit(@Param("providerId") int providerId,@Param("credit") int credit,@Param("balance") double balance);
    // 修改买家余额
    int updateEBalance(@Param("employerId")int employerId,@Param("balance") double balance);
    // 添加信誉记录
    int addCreditDetails(@Param("userId")int userId,@Param("remarks")String remarks,@Param("creditTime")String creditTime,@Param("type") String type,@Param("score") String score);
    // 添加流水表
    int addCapitalFlow(@Param("tradeType")String tradeType,@Param("phoneNumber") String phoneNumber,@Param("tradeContent") String tradeContent,@Param("tradeCapital")int tradeCapital,@Param("tradeTime")String tradeTime,@Param("tradeState")String tradeState,@Param("type")String type);
    // 查询服务商账号
}
