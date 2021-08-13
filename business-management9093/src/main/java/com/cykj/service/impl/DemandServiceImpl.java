package com.cykj.service.impl;

import com.cykj.bean.CapitalFlow;
import com.cykj.bean.Demand;
import com.cykj.bean.TenderRecord;
import com.cykj.bean.TradeRecord;
import com.cykj.mapper.DemandMapper;
import com.cykj.mapper.EmpCenterMapper;
import com.cykj.mapper.TenderRecodeMapper;
import com.cykj.mapper.TradeRecordMapper;
import com.cykj.service.DemandService;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/4 16:45
 * @desc:
 */
@Service
public class DemandServiceImpl implements DemandService {
	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private TenderRecodeMapper tenderRecodeMapper;
	@Autowired
	private TradeRecordMapper tradeRecordMapper;
	@Autowired
	private EmpCenterMapper empCenterMapper;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	@Override
	public List<Map<String, Object>> queryAllDemand(int count, String type, String price, String time, String sort, String searchInfo) {
		int leftPrice = -1, rightPrice = -1, leftTime = -1 , rightTime = -1;
		String sortType = null;
		switch (price){
			case "0-5K":
				leftPrice = 0;
				rightPrice = 5000;
				break;

			case "5K-10K":
				leftPrice = 5000;
				rightPrice = 10000;
				break;
			case "10K-50K":
				leftPrice = 10000;
				rightPrice = 50000;
				break;
			case "50K以上":
				leftPrice = 50000;
				break;
		}
		switch (time){
				case "小于10周":
				rightTime = 10;
				break;

			case "10周到30周":
				leftTime = 10;
				rightTime = 30;
				break;
			case "大于30周":
				leftTime = 30;
				break;
		}
		switch (sort) {
			case "时间降序":
				sortType = "releaseTime desc";
				break;
			case "时间升序":
				sortType = "releaseTime asc";
				break;
			case "价格降序":
				sortType = "predictPrice desc";
				break;
			case "价格升序":
				sortType = "predictPrice asc";
				break;

		}
		return demandMapper.queryAllDemand(count, type, leftPrice, rightPrice, leftTime, rightTime, sortType, searchInfo);
	}

	@Override
	public boolean joinDemand(TenderRecord tenderRecord, MultipartFile file) {
		String realPath = null;
		try {
			realPath = ResourceUtils.getURL("classpath:").getPath() + "file";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		File uploadDir = new File(realPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
		System.out.println(realPath);
        tenderRecord.setAnnexPath(file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		tenderRecord.setExistTime(sdf.format(new Date()));
		try {
			file.transferTo(new File(realPath + '/' + tenderRecord.getAnnexPath()));
			if(tenderRecodeMapper.insertRecode(tenderRecord) > 0) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> queryDemandById(int employerId) {
		return demandMapper.queryDemandById(employerId);
	}
	@Override
	public List<Map<String, Object>> queryTenderRecordById(int employerId) {
		return tenderRecodeMapper.queryTenderRecordById(employerId);
	}

	@Override
	public boolean refuseTender(TenderRecord tenderRecord) {
		tenderRecord.setApplyStatus(2);
		if(tenderRecodeMapper.updateRecode(tenderRecord) > 0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean acceptTender(TenderRecord tenderRecord) {

		// 插入需求交易记录表
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setTenderRecordId(tenderRecord.getTenderRecordId());
		tradeRecord.setTradeStatus(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		tradeRecord.setTradeTime(sdf.format(new Date()));
		if(tradeRecordMapper.insertRecord(tradeRecord) > 0){
			// 投标记录申请状态改为 1 已通过
			tenderRecord.setApplyStatus(1);
			tenderRecodeMapper.updateRecode(tenderRecord);
			// 需求状态改为 4 开发中
			demandMapper.updateDemandStatus(tenderRecord.getDemandId(), 4);
			return true;
		} else {
			return false;
		}
	}
	@Override
	public List<Map<String, Object>> queryTenderById(int providerId) {
		return tenderRecodeMapper.queryTenderById(providerId);
	}
	@Override
	public Map<String, Object> queryTenderProviderById(int demandId){
		return tenderRecodeMapper.queryTenderProviderById(demandId);
	}
	@Override
	public boolean recruitProvider(int demandId){
		if(demandMapper.updateDemandStatus(demandId, 3) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean advanceCharge(int employerId, int tradeRecordId) {
		Map<String, Object> tradeRecord = tradeRecordMapper.queryTradeRecordById(tradeRecordId);

		double price = Double.valueOf(tradeRecord.get("price").toString());
		System.out.println("承接价格为：" + price);
		System.out.println("雇主ID为 " + employerId + " 扣款：" + price + " 的30%");
		System.out.println("服务商ID为 " + tradeRecord.get("tenderId") + " 收款：" + price + " 的30%（可能扣除一点手续费）");
		System.out.println("交易记录ID 为 " + tradeRecordId +  " 的交易状态从 0 预付款 更改为 1 等待交付");
		tradeRecordMapper.updateRecord(tradeRecordId, 1);
		CapitalFlow capitalFlow = new CapitalFlow();
		// 交易流水 -- 雇主
		capitalFlow.setPhoneNumber(empCenterMapper.selempInfo(employerId).get("phoneNumber").toString());
		capitalFlow.setTradeCapital(price * 0.3);
		capitalFlow.setType("雇主");
		capitalFlow.setTradeType("开发宝");
		capitalFlow.setTradeTime(sdf.format(new Date()));
		capitalFlow.setTradeContent("支付需求《" + tradeRecord.get("demandTitle") + "》预付款");
		capitalFlow.setTradeState("ACQ.TRADE_HAS_SUCCESS");
		empCenterMapper.addFlow(capitalFlow);
		// 交易流水 -- 服务商
		capitalFlow.setPhoneNumber(tradeRecord.get("phoneNumber").toString());
		capitalFlow.setTradeCapital(price * 0.3);
		capitalFlow.setType("服务商");
		capitalFlow.setTradeType("开发宝");
		capitalFlow.setTradeTime(sdf.format(new Date()));
		capitalFlow.setTradeContent("收到需求《" + tradeRecord.get("demandTitle") + "》预付款");
		capitalFlow.setTradeState("ACQ.TRADE_HAS_SUCCESS");
		empCenterMapper.addFlow(capitalFlow);
		return false;
	}

	@Override
	public boolean deliveryProject(int tradeRecordId, int demandId) {
		demandMapper.updateDemandStatus(demandId, 5);
		tradeRecordMapper.updateRecord(tradeRecordId, 2);
		return false;
	}

	@Override
	public boolean checkProject(int tradeRecordId) {
		tradeRecordMapper.updateRecord(tradeRecordId, 3);
		return false;
	}
	@Override
	public boolean restCharge(int employerId, int tradeRecordId) {
		Map<String, Object> tradeRecord = tradeRecordMapper.queryTradeRecordById(tradeRecordId);
		double price = Double.valueOf(tradeRecord.get("price").toString());
		System.out.println("承接价格为：" + tradeRecord.get("price"));
		System.out.println("雇主ID为 " + employerId + " 扣款：" + tradeRecord.get("price") + " 的70%");
		System.out.println("服务商ID为 " + tradeRecord.get("tenderId") + " 收款：" + tradeRecord.get("price") + " 的70%（可能扣除一点手续费）");
		System.out.println("交易记录ID 为 " + tradeRecordId +  " 的交易状态从 3 付尾款 更改为 4 交易完成");
		tradeRecordMapper.updateRecord(tradeRecordId, 4);
		System.out.println("需求ID 为 " + tradeRecord.get("demandId") +  " 的状态从 5 交付中 更改为 6 已完成完成");
		demandMapper.updateDemandStatus(Integer.valueOf(tradeRecord.get("demandId").toString()), 6);
		CapitalFlow capitalFlow = new CapitalFlow();
		// 交易流水 -- 雇主
		capitalFlow.setPhoneNumber(empCenterMapper.selempInfo(employerId).get("phoneNumber").toString());
		capitalFlow.setTradeCapital(price * 0.7);
		capitalFlow.setType("雇主");
		capitalFlow.setTradeType("开发宝");
		capitalFlow.setTradeTime(sdf.format(new Date()));
		capitalFlow.setTradeContent("支付需求《" + tradeRecord.get("demandTitle") + "》尾款");
		capitalFlow.setTradeState("ACQ.TRADE_HAS_SUCCESS");
		empCenterMapper.addFlow(capitalFlow);
		// 交易流水 -- 服务商
		capitalFlow.setPhoneNumber(tradeRecord.get("phoneNumber").toString());
		capitalFlow.setTradeCapital(price * 0.7);
		capitalFlow.setType("服务商");
		capitalFlow.setTradeType("开发宝");
		capitalFlow.setTradeTime(sdf.format(new Date()));
		capitalFlow.setTradeContent("收到需求《" + tradeRecord.get("demandTitle") + "》尾款");
		capitalFlow.setTradeState("ACQ.TRADE_HAS_SUCCESS");
		empCenterMapper.addFlow(capitalFlow);
		return false;
	}

	@Override
	public boolean toEvaluate(int demandId) {
		// 需求状态更改为 6 已完成
		demandMapper.updateDemandStatus(demandId, 6);
		return false;
	}

	@Override
	public Map<String, Object> findEmployerInfo(int demandId) {

		return demandMapper.queryEmployerByDemandId(demandId);
	}
}
