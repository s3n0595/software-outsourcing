package com.cykj.mapper;

import com.cykj.bean.TenderRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/9 13:33
 * @desc:
 */
@Repository
public interface TenderRecodeMapper {
	int insertRecode(TenderRecord tenderRecord);
	List<Map<String, Object>> queryTenderRecordById(int demandId);
	int deleteRecode(TenderRecord tenderRecord);
	List<Map<String, Object>> queryTenderById(int provider);
	int updateRecode(TenderRecord tenderRecord);
	Map<String, Object> queryTenderProviderById(int demandId);

	List<Map<String, Object>> queryTenderUnion(int demandId);
	List<Map<String, Object>> queryTenderSingle(int demandId);
}
