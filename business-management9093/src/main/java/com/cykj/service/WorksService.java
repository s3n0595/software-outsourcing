package com.cykj.service;

import com.cykj.bean.Works;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/3 16:32
 * @desc:
 */
public interface WorksService {
	boolean publishWorks(MultipartFile file, Works works);
	int insertWorks(Works works);
	boolean updateWorks(Works works);
	Map<String, String> uploadFile(MultipartFile[] file);
	List<Works> queryAllWorks();
	List<Works> queryMyWorks(int providerId);
	int updatePwd(int providerId, String providerPassword,String password);

}
