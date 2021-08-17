package com.cykj.controller;

import com.cykj.bean.Demand;
import com.cykj.bean.DemandWork;
import com.cykj.bean.TenderRecord;
import com.cykj.service.ChatService;
import com.cykj.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
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
	@Autowired
	private ChatService chatService;
	@RequestMapping("/list")
	@ResponseBody
	public List<Map<String, Object>> queryDemandList(int count, String type, String price, String time, String sort, String searchInfo){
		System.out.println("================需求列表===========");
		System.out.println("count : " + count);
		return demandService.queryAllDemand(count, type, price, time, sort, searchInfo);
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
	@RequestMapping("/recruitProvider")
	@ResponseBody
	public void recruitProvider(int demandId){
		System.out.println("================需求状态更改为招募中===========");
		System.out.println(demandId);
		demandService.recruitProvider(demandId);
	}
	@RequestMapping("/tenderList")
	@ResponseBody
	public List<Map<String, Object>> tenderList(int demandId){
		System.out.println("===============投标人列表===========");
		System.out.println(demandId);
		return demandService.queryTenderRecordById(demandId);
	}

	@RequestMapping("/tenderProvider")
	@ResponseBody
	public Map<String, Object> tenderProvider(int demandId){
		System.out.println("==============成功投标的服务商===========");
		System.out.println(demandId);
		return demandService.queryTenderProviderById(demandId);
	}
	@RequestMapping("/acceptTender")
	@ResponseBody
	public String acceptTender(TenderRecord tenderRecord){
		System.out.println("===============通过投标人===========");
		System.out.println(tenderRecord);
		if(demandService.acceptTender(tenderRecord)) {
			chatService.createRoom(tenderRecord);
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
	@RequestMapping("/myTender")
	@ResponseBody
	public List<Map<String, Object>> myTenderList(int providerId){
		System.out.println("===============服务商投标列表===========");
		System.out.println(providerId);
		return demandService.queryTenderById(providerId);
	}
	@RequestMapping("/advanceCharge")
	@ResponseBody
	public String advanceCharge(int employerId, int tradeRecordId){
		System.out.println("===============雇主预付款===========");
		System.out.println(employerId);
		System.out.println(tradeRecordId);
		return demandService.advanceCharge(employerId, tradeRecordId);
	}
	@RequestMapping("/deliveryProject")
	@ResponseBody
	public void deliveryProject(int tradeRecordId, int demandId){
		System.out.println("===============开发商交付项目===========");
		System.out.println(tradeRecordId);
		demandService.deliveryProject(tradeRecordId, demandId);
	}
	@RequestMapping("/checkProject")
	@ResponseBody
	public void checkProject(int tradeRecordId){
		System.out.println("===============雇主核验项目===========");
		System.out.println(tradeRecordId);
		demandService.checkProject(tradeRecordId);
	}
	@RequestMapping("/restCharge")
	@ResponseBody
	public String restCharge(int employerId, int tradeRecordId){
		System.out.println("===============雇主付尾款===========");
		System.out.println(employerId);
		System.out.println(tradeRecordId);
		return demandService.restCharge(employerId, tradeRecordId);
	}
	@RequestMapping("/toEvaluate")
	@ResponseBody
	public void toEvaluate(int demandId){
		System.out.println("===============雇主评价===========");
		demandService.toEvaluate(demandId);
	}
	@RequestMapping("/findEmployerInfo")
	@ResponseBody
	public Map<String, Object> findEmployerInfo(int demandId){
		System.out.println("===============需求雇主信息===========");
		return demandService.findEmployerInfo(demandId);
	}

	@RequestMapping("/comparePayPwd")
	@ResponseBody
	public String comparePayPwd(int employerId){
		System.out.println("===============比较支付密码===========");
		System.out.println(employerId);
		return demandService.comparePayPwd(employerId);
//		return demandService.findEmployerInfo(demandId);
	}
	@RequestMapping("/uploadProject")
	@ResponseBody
	public void uploadProject(MultipartFile file, DemandWork demandWork){
		System.out.println("===============提交项目===========");
		demandService.uploadProject(file, demandWork);

//		return demandService.findEmployerInfo(demandId);
	}

}
