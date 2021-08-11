package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/11 0:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDetails {
    private int creditId;
    private int userId;
    private String remarks;
    private String creditTime;
    private String type;
}
