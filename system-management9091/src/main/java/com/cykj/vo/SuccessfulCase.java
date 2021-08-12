package com.cykj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guoquansen
 * @date 2021/8/12 11:54 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulCase {

    private int tradeWorksId;
    private int tradeStatus;
    private String tradeTime;
    private int showCase;
    private String employerName;
    private String providerName;
    private String worksTitle;
    private String worksDescribe;
    private String annexPath;
    private String worksPrice;
}
