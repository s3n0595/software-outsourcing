package com.cykj.controller;

import com.cykj.bean.Demand;
import com.cykj.bean.TenderRecord;
import com.cykj.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public String joinDemand(MultipartFile file, TenderRecord tenderRecord){
		System.out.println("================参与项目===========");
		System.out.println(tenderRecord);
		System.out.println(file.getOriginalFilename());
		if(demandService.joinDemand(tenderRecord, file)){
			return "success";
		} else {
			return "failed";
		}
	}
	@RequestMapping("/myDemand")
	@ResponseBody
	public List<Map<String, Object>> demandList(int employerId){
		System.out.println("================我的项目列表===========");
		System.out.println(employerId);
		return demandService.queryDemandById(employerId);
	}
	@RequestMapping("/tenderList")
	@ResponseBody
	public List<Map<String, Object>> tenderList(int demandId){
		System.out.println("===============投标人列表===========");
		System.out.println(demandId);
		return demandService.queryTenderRecordById(demandId);
	}
	@RequestMapping("/acceptTender")
	@ResponseBody
	public String acceptTender(TenderRecord tenderRecord){
		System.out.println("===============通过投标人===========");
		System.out.println(tenderRecord);
		if(demandService.acceptTender(tenderRecord)) {
			return "success";
		} else {
			return "failed";
		}
	}
	@RequestMapping("/refuseTender")
	@ResponseBody
	public String refuseTender(TenderRecord tenderRecord){
		System.out.println("===============拒绝投标人===========");
		System.out.println(tenderRecord);
		if(demandService.refuseTender(tenderRecord)) {
			return "success";
		} else {
			return "failed";
		}
	}

}
