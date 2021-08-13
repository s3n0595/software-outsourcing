package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/11 19:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealtimeInfo {
    private String img;
    private String title;
    private String content;
    private String tags1;
    private String tags2;
    private String tags3;
    private String time;
}
