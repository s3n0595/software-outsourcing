package com.cykj.mapper;

import com.cykj.bean.Works;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/3 16:47
 * @desc:
 */
@Repository
public interface WorksMapper {
	int addWorks(Works works);
	List<Works> queryAllWorks();
	List<Works> queryMyWorks(int providerId);
	int updatePwd(@Param("providerId") int providerId, @Param("providerPassword") String providerPassword, @Param("password") String password);
	int updateTraffic(int worksId);
	int insertWorks(Works works);
	int updateImgUrl(Works works);
	int selectLastWork();

}
