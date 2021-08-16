package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/13 12:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TradeRetreat extends TradeWork {
    private int tradeRetreatId;
    private int tradeWorksId;
    // 1:退款 2：成功 3：失败
    private int retreatStatus;
    private String remarks;
    private String tradeRetreatTime;
    private TradeWork tradeWork;
}
