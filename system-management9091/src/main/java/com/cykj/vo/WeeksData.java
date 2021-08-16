package com.cykj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author guoquansen
 * @date 2021/8/14 10:07 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeksData {

    private Date datetime;
    private int count;


}
