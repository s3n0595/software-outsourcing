package com.cykj.service;

import com.cykj.bean.Demand;
import com.cykj.bean.DemandWork;
import com.cykj.bean.TenderRecord;
import com.cykj.bean.TradeRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/4 16:44
 * @desc:
 */
public interface DemandService {
	List<Map<String, Object>> queryAllDemand(int count, String type, String price, String time, String sort, String searchInfo);
	boolean joinDemand(TenderRecord tenderRecord, MultipartFile file);
	List<Map<String, Object>> queryDemandById(int employerId);
	List<Map<String, Object>> queryTenderRecordById(int demandId);
	boolean refuseTender(TenderRecord tenderRecord);
	boolean acceptTender(TenderRecord tenderRecord);
	List<Map<String, Object>> queryTenderById(int providerId);
	Map<String, Object> queryTenderProviderById(int demandId);
	boolean recruitProvider(int demandId);

	String advanceCharge(int employerId, int tradeRecordId);
	boolean deliveryProject(int tradeRecordId, int demandId);
	boolean checkProject(int tradeRecordId);
	String restCharge(int employerId, int tradeRecordId);
	boolean toEvaluate(int demandId);
	Map<String, Object> findEmployerInfo(int demandId);
	void increaseTraffic(int demandId);
	String comparePayPwd(int employerId);
	boolean uploadProject(MultipartFile file, DemandWork demandWork);
}
