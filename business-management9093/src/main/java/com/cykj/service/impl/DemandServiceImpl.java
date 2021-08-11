package com.cykj.service.impl;

import com.cykj.bean.Demand;
import com.cykj.bean.TenderRecord;
import com.cykj.mapper.DemandMapper;
import com.cykj.mapper.TenderRecodeMapper;
import com.cykj.service.DemandService;
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
	@Override
	public List<Map<String, Object>> queryAllDemand(int count) {
		return demandMapper.queryAllDemand(count);
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
		if(tenderRecodeMapper.deleteRecode(tenderRecord) > 0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean acceptTender(TenderRecord tenderRecord) {
		demandMapper.updateDemandStatus(tenderRecord.getDemandId(), 4);
		return false;
	}
}
