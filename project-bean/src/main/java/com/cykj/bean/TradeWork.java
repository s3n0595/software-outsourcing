package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/8 17:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TradeWork {
    private int tradeWorksId;
    private int employerId;
    private int worksId;
    private String tradeTime;
    private int tradeStatus;
    private String tradeOrder;
}
