package com.cykj.mapper;

import com.cykj.bean.Demand;
import com.cykj.bean.DemandWork;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/4 16:45
 * @desc:
 */
@Repository
public interface DemandMapper {
	List<Map<String, Object>> queryAllDemand(@Param("count") int count,@Param("type") String type, @Param("leftPrice") int leftPrice, @Param("rightPrice") int rightPrice, @Param("leftTime") int leftTime, @Param("rightTime") int rightTime, @Param("sortType") String sortType, @Param("searchInfo") String searchInfo);
	List<Map<String, Object>> queryDemandById(int employerId);
	int updateDemandStatus(@Param("demandId") int demandId, @Param("status") int status);
	Demand selectDemandById(int demandId);
	Map<String, Object> queryEmployerByDemandId(int demandId);
	int updateTraffic(int demandId);
	String selectEmployerPwd(int employerId);
	double selectEmployerBalance(int employerId);
	int uploadProject(DemandWork demandWork);
}
