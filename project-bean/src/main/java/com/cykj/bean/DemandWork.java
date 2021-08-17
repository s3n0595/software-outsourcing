package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/17 11:21
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandWork {
	private int demandWorkId;
	private int demandId;
	private String annexPath;
	private String onlineAddress;
}
