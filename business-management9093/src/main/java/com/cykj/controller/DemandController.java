package com.cykj.controller;

import com.cykj.bean.Demand;
import com.cykj.bean.TenderRecord;
import com.cykj.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/4 16:44
 * @desc:
 */
@Controller
@RequestMapping("/demand")
public class DemandController {
	@Autowired
	private DemandService demandService;
	@RequestMapping("/list")
	@ResponseBody
	public List<Map<String, Object>> queryDemandList(int count){
		System.out.println("================需求列表===========");
		System.out.println("count : " + count);
		return demandService.queryAllDemand(count);
	}
	@RequestMapping("/join")
	@ResponseBody
	public String joinDemand(TenderRecord tenderRecord){
		System.out.println("================参与项目===========");
		System.out.println(tenderRecord);
		if(demandService.joinDemand(tenderRecord)){
			return "success";
		} else {
			return "failed";
		}

	}
}
