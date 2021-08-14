package com.cykj.controller;

import com.cykj.mapper.WorksMapper;
import com.cykj.service.DemandService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/13 23:37
 * @desc:
 */
@Controller
@Slf4j
@RequestMapping("traffic")
public class TrafficController {
	@Autowired
	private DemandService demandService;
	@Autowired
	private WorksMapper worksMapper;
	@RequestMapping("/increase")
	@ResponseBody
	public void increaseTraffic(int id, String type){
		System.out.println("===============增加访问量===========");
		if(type.equals("demand")) {
			demandService.increaseTraffic(id);
		} else if(type.equals("work")) {
			worksMapper.updateTraffic(id);
		}
	}
}
