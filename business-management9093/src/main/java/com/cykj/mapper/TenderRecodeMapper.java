package com.cykj.mapper;

import com.cykj.bean.TenderRecord;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/9 13:33
 * @desc:
 */
@Repository
public interface TenderRecodeMapper {
	int insertRecode(TenderRecord tenderRecord);
}
