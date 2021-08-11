package com.cykj.service;

import com.cykj.bean.Demand;
import com.cykj.bean.TenderRecord;
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
	List<Map<String, Object>> queryAllDemand(int count);
	boolean joinDemand(TenderRecord tenderRecord, MultipartFile file);
	List<Map<String, Object>> queryDemandById(int employerId);
	List<Map<String, Object>> queryTenderRecordById(int demandId);
	boolean refuseTender(TenderRecord tenderRecord);
	boolean acceptTender(TenderRecord tenderRecord);
}
